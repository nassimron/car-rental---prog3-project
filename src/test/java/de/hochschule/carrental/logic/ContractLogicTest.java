package de.hochschule.carrental.logic;

import de.hochschule.carrental.data.Car;
import de.hochschule.carrental.data.Contract;
import de.hochschule.carrental.data.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ContractLogicTest {

    private ContractLogic contractLogic;

    @BeforeEach
    void setUp() {
        contractLogic = new ContractLogic();
    }

    private Customer validCustomer() {
        return new Customer("C1", "Barack Obama", "D12345", "barack.obama@gmail.com");
    }


    private Car validCarAvailable() {

        return new Car("1", "BMW", "320i", "Limousine", 100, true);
    }

    @Test
    void createContract_validInput_createsContractAddsToListAndSetsCarUnavailable() {
        Customer customer = validCustomer();
        Car car = validCarAvailable();
        LocalDate begin = LocalDate.of(2026, 2, 1);
        LocalDate end = LocalDate.of(2026, 2, 3); // 3 Tage (1,2,3)

        Contract contract = contractLogic.createContract(customer, car, begin, end);

        assertNotNull(contract);
        assertNotNull(contract.getID());
        assertEquals(1, contractLogic.getAllContracts().size());

        assertFalse(car.getAvailability());

        assertSame(contract, contractLogic.getContractById(contract.getID()));
    }

    @Test
    void createContract_nullCustomer_throwsIllegalArgumentException() {
        Car car = validCarAvailable();

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> contractLogic.createContract(null, car, LocalDate.now(), LocalDate.now()));

        assertEquals("Customer must not be empty", ex.getMessage());
    }

    @Test
    void createContract_nullCar_throwsIllegalArgumentException() {
        Customer customer = validCustomer();

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> contractLogic.createContract(customer, null, LocalDate.now(), LocalDate.now()));

        assertEquals("Car must not be empty", ex.getMessage());
    }

    @Test
    void createContract_nullDates_throwsIllegalArgumentException() {
        Customer customer = validCustomer();
        Car car = validCarAvailable();

        IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class,
                () -> contractLogic.createContract(customer, car, null, LocalDate.now()));
        assertEquals("Dates must not be empty", ex1.getMessage());

        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class,
                () -> contractLogic.createContract(customer, car, LocalDate.now(), null));
        assertEquals("Dates must not be empty", ex2.getMessage());
    }

    @Test
    void createContract_endBeforeBegin_throwsIllegalArgumentException() {
        Customer customer = validCustomer();
        Car car = validCarAvailable();
        LocalDate begin = LocalDate.of(2026, 2, 10);
        LocalDate end = LocalDate.of(2026, 2, 9);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> contractLogic.createContract(customer, car, begin, end));

        assertEquals("End date must be >= begin date", ex.getMessage());
    }

    @Test
    void createContract_carNotAvailable_throwsIllegalArgumentException() {
        Customer customer = validCustomer();
        Car car = validCarAvailable();
        car.setAvailability(false);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> contractLogic.createContract(customer, car, LocalDate.now(), LocalDate.now()));

        assertEquals("Car is not available", ex.getMessage());
    }

    @Test
    void createContract_generatesIncrementingIds() {
        Customer customer = validCustomer();
        Car car1 = new Car("1", "BMW", "320i", "Limousine", 100, true);
        Car car2 = new Car("2", "Audi", "A4", "Limousine", 120, true);

        Contract c1 = contractLogic.createContract(customer, car1,
                LocalDate.of(2026, 2, 1), LocalDate.of(2026, 2, 1));
        Contract c2 = contractLogic.createContract(customer, car2,
                LocalDate.of(2026, 2, 2), LocalDate.of(2026, 2, 2));

        assertEquals("V1", c1.getID());
        assertEquals("V2", c2.getID());
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
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> contractLogic.calcPrice(
                        100,
                        LocalDate.of(2026, 2, 10),
                        LocalDate.of(2026, 2, 9)
                )
        );
        assertEquals("Invalid contract duration", ex.getMessage());
    }

    @Test
    void finishContract_existingContract_makesCarAvailableAndRemovesContract() {
        Customer customer = validCustomer();
        Car car = validCarAvailable();
        Contract contract = contractLogic.createContract(customer, car,
                LocalDate.of(2026, 2, 1), LocalDate.of(2026, 2, 2));

        assertFalse(car.getAvailability());
        assertEquals(1, contractLogic.getAllContracts().size());

        contractLogic.finishContract(contract.getID());

        assertTrue(car.getAvailability());
        assertEquals(0, contractLogic.getAllContracts().size());
        assertNull(contractLogic.getContractById(contract.getID()));
    }

    @Test
    void finishContract_unknownId_throwsIllegalArgumentException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> contractLogic.finishContract("V999"));

        assertEquals("Contract not found: V999", ex.getMessage());
    }
}