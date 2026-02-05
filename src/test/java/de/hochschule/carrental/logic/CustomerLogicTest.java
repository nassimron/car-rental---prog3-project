package de.hochschule.carrental.logic;

import de.hochschule.carrental.data.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class CustomerLogicTest {

    private CustomerLogic customerLogic;

    @BeforeEach
    void setUp() throws Exception {

        try (Connection conn = Database.connect();
             Statement stmt = conn.createStatement()) {

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS customers(
                    id TEXT PRIMARY KEY,
                    name TEXT,
                    dl_number TEXT UNIQUE,
                    email TEXT
                )
            """);

            stmt.execute("DELETE FROM customers");

            stmt.execute("INSERT INTO customers VALUES('K1','Max Mustermann','DL1','max@test.de')");
            stmt.execute("INSERT INTO customers VALUES('K5','Erika Muster','DL5','erika@test.de')");
        }

        customerLogic = new CustomerLogic();
    }

    @Test
    void getCustomerById_nonExisting_returnsNull() {
        assertNull(customerLogic.getCustomerById("does-not-exist"));
    }

    @Test
    void getNextCustomerNumber_returnsMaxPlusOne() {
        assertEquals(6, customerLogic.getNextCustomerNumber());
    }

    @Test
    void create_addsCustomer_and_generatesId() {
        var created = customerLogic.create("Barack Obama", "DL999", "barack@test.de");

        assertNotNull(created);
        assertEquals("K6", created.getID());
        assertNotNull(customerLogic.getCustomerById("K6"));
    }

    @Test
    void create_invalidEmail_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> customerLogic.create("Test", "DL777", "not-an-email"));
    }
}
