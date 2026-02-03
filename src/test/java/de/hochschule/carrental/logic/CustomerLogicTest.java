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
    void create_validInput_addsCustomerAndReturnsIt() {
        Customer c = invokeCreate("C1", "Barack Obama", "DL12345", "barack.obama@gmail.com");

        assertNotNull(c);
        assertEquals("C1", c.getID());
        assertEquals(1, customerLogic.getAllCustomers().size());
        assertSame(c, customerLogic.getCustomerById("C1"));
    }

    @Test
    void create_nullId_throwsIllegalArgumentException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> invokeCreate(null, "Barack Obama", "DL12345", "barackobama@gmail.com"));

        assertEquals("Customer must not be empty", ex.getMessage());
    }

    @Test
    void create_blankName_throwsIllegalArgumentException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> invokeCreate("C1", "   ", "DL12345", "barackobama@gmail.com"));

        assertEquals("Name must not be empty", ex.getMessage());
    }

    @Test
    void create_blankDLN_throwsIllegalArgumentException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> invokeCreate("C1", "Barack", "   ", "barackobama@gmail.com"));

        assertEquals("DLN must not be empty", ex.getMessage());
    }

    @Test
    void create_blankEmail_throwsIllegalArgumentException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> invokeCreate("C1", "Barack Obama", "DL1", "   "));

        assertEquals("Email must not be empty", ex.getMessage());
    }

    @Test
    void getCustomerById_nonExisting_returnsNull() {
        assertNull(customerLogic.getCustomerById("does-not-exist"));
    }

    @Test
    void delete_existingCustomer_removesIt() {
        invokeCreate("C1", "Barack Obama", "DL12345", "barackobama@gmail.com");
        invokeCreate("C2", "Angela Merkel", "DL23456", "angelamerkel@gmail.com");

        customerLogic.delete("C1");

        assertNull(customerLogic.getCustomerById("C1"));
        assertNotNull(customerLogic.getCustomerById("C2"));
        assertEquals(1, customerLogic.getAllCustomers().size());
    }

    @Test
    void delete_nonExistingCustomer_doesNothing() {
        invokeCreate("C1", "Barack Obama", "DL12345", "barackobama@gmail.com");

        customerLogic.delete("X");

        assertEquals(1, customerLogic.getAllCustomers().size());
    }
}
