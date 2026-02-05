package de.hochschule.carrental.logic;

import de.hochschule.carrental.data.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerLogicTest {

    private CustomerLogic customerLogic;

    @BeforeEach
    void setUp() {
        customerLogic = new CustomerLogic();
    }

    private Customer invokeCreate(String id, String name, String dln, String email) {
        try {
            Method m = CustomerLogic.class.getDeclaredMethod(
                    "create", String.class, String.class, String.class, String.class);
            m.setAccessible(true);
            return (Customer) m.invoke(customerLogic, id, name, dln, email);
        } catch (Exception e) {
            throw (RuntimeException) e.getCause();
        }
    }

    @Test
    void getCustomerById_nonExisting_returnsNull() {
        assertNull(customerLogic.getCustomerById("does-not-exist"));
    }
}
