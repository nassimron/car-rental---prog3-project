package de.hochschule.carrental.logic;

import de.hochschule.carrental.data.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ContractLogicTest {

    private ContractLogic contractLogic;

    @BeforeEach
    void setUp() throws Exception {

        try (Connection conn = Database.connect();
             Statement stmt = conn.createStatement()) {

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS cars (
                    id TEXT PRIMARY KEY,
                    brand TEXT,
                    model TEXT,
                    category TEXT,
                    price INTEGER,
                    available BOOLEAN
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS customers (
                    id TEXT PRIMARY KEY,
                    name TEXT,
                    dl_number TEXT,
                    email TEXT
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS contracts (
                    id TEXT PRIMARY KEY,
                    customer_id TEXT,
                    car_id TEXT,
                    begin_date TEXT,
                    end_date TEXT,
                    price INTEGER
                )
            """);

            stmt.execute("DELETE FROM contracts");
            stmt.execute("DELETE FROM customers");
            stmt.execute("DELETE FROM cars");

            stmt.execute("INSERT INTO cars VALUES('A1','BMW','320','Limousine',100,true)");
            stmt.execute("INSERT INTO customers VALUES('K1','Max Mustermann','DL1','max@test.de')");
        }

        contractLogic = new ContractLogic();
    }


    @Test
    void calcPrice_sameDay_countsAsOneDay() {
        int price = contractLogic.calcPrice(
                100,
                LocalDate.of(2026, 2, 3),
                LocalDate.of(2026, 2, 3)
        );
        assertEquals(100, price);
    }

    @Test
    void calcPrice_threeDays_isThreeTimesCarPrice() {
        int price = contractLogic.calcPrice(
                50,
                LocalDate.of(2026, 2, 1),
                LocalDate.of(2026, 2, 3)
        );
        assertEquals(150, price);
    }

    @Test
    void calcPrice_endBeforeBegin_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> contractLogic.calcPrice(
                        100,
                        LocalDate.of(2026, 2, 10),
                        LocalDate.of(2026, 2, 9)
                )
        );
    }


    @Test
    void createContract_savesContract_and_setsCarUnavailable() throws Exception {

        var customer = contractLogic.getCustomerLogic().getCustomerById("K1");
        var car = contractLogic.getCarLogic().getCarById("A1");

        assertNotNull(customer);
        assertNotNull(car);
        assertTrue(car.getAvailability());

        var contract = contractLogic.createContract(
                customer,
                car,
                LocalDate.of(2026, 2, 1),
                LocalDate.of(2026, 2, 3)
        );

        assertNotNull(contract);
        assertNotNull(contract.getID());
        assertEquals(300, contract.getPrice()); // 3 Tage * 100

        assertNotNull(contractLogic.getContractById(contract.getID()));

        assertFalse(contractLogic.getCarLogic().getCarById("A1").getAvailability());

        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) AS c FROM contracts WHERE id = ?")) {

            ps.setString(1, contract.getID());
            try (ResultSet rs = ps.executeQuery()) {
                assertTrue(rs.next());
                assertEquals(1, rs.getInt("c"));
            }
        }

        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement("SELECT available FROM cars WHERE id = ?")) {

            ps.setString(1, "A1");
            try (ResultSet rs = ps.executeQuery()) {
                assertTrue(rs.next());
                assertFalse(rs.getBoolean("available"));
            }
        }
    }

    @Test
    void finishContract_deletesContract_and_setsCarAvailableAgain() throws Exception {

        var customer = contractLogic.getCustomerLogic().getCustomerById("K1");
        var car = contractLogic.getCarLogic().getCarById("A1");

        var contract = contractLogic.createContract(
                customer,
                car,
                LocalDate.of(2026, 2, 1),
                LocalDate.of(2026, 2, 2)
        );

        String contractId = contract.getID();

        assertNotNull(contractLogic.getContractById(contractId));
        assertFalse(contractLogic.getCarLogic().getCarById("A1").getAvailability());

        contractLogic.finishContract(contractId);

        assertNull(contractLogic.getContractById(contractId));

        assertTrue(contractLogic.getCarLogic().getCarById("A1").getAvailability());

        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) AS c FROM contracts WHERE id = ?")) {

            ps.setString(1, contractId);
            try (ResultSet rs = ps.executeQuery()) {
                assertTrue(rs.next());
                assertEquals(0, rs.getInt("c"));
            }
        }

        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement("SELECT available FROM cars WHERE id = ?")) {

            ps.setString(1, "A1");
            try (ResultSet rs = ps.executeQuery()) {
                assertTrue(rs.next());
                assertTrue(rs.getBoolean("available"));
            }
        }
    }
}
