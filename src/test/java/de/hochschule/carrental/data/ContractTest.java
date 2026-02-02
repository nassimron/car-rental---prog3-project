package de.hochschule.carrental.data;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContractTest {

    private Contract contract;
    private Customer customer;
    private Car car;

    @BeforeEach
    void setUp() {
        customer = new Customer("U001", "Barack Obama", "DL12345", "barack.obama@gmail.com");
        car = new Car("C01", "Volkswagen", "Amarok", "SUV", 299.99f, true);
        contract = new Contract("V001", customer, car, "2026-01-01", "2026-01-10", 2999.99f);
    }

    @Test
    void testEmptyConstructor() {
        Contract emptyContract = new Contract();
        assertNotNull(emptyContract);
    }

    @Test
    void testContructorAndGetters() {
        assertEquals("V001", contract.getID());
        assertEquals(customer, contract.getCustomer());
        assertEquals(car, contract.getCar());
        assertEquals("2026-01-01", contract.getBeginDate());
        assertEquals("2026-01-10", contract.getEndDate());
    }

    @Test
    void testSetID() {
        contract.setID("V002");
        assertEquals("V002", contract.getID());
    }

    @Test
    void testSetCustomer(){
        Customer newCustomer = new Customer("002", "Angela Merkel", "DL-54321", "angela.merkel@gmail.com");
        contract.setCustomer(newCustomer);
        assertEquals(newCustomer, contract.getCustomer());
    }

    @Test
    void testSetCar() {
        Car newCar = new Car ("C002", "Ferrari","F40", "Sportwagen", 450.00f, true );
        contract.setCar(newCar);
        assertEquals(newCar, contract.getCar());
    }

    @Test
    void testSetBeginDate() {
        contract.setBeginDate("2026-02-01");
        assertEquals("2026-02-01", contract.getBeginDate());
    }

    @Test
    void testSetEndDate() {
        contract.setEndDate("2026-02-12");
        assertEquals("2026-02-12", contract.getEndDate());
    }

    @Test
    void testSetPrice() {
        contract.setPrice(1200.50f);
        assertEquals(1200.50f, contract.getPrice());
    }
}
