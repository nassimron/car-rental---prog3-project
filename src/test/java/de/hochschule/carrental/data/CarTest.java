package de.hochschule.carrental.data;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CarTest {

    private Car car;

    @BeforeEach
    void setUp() {
        car = new Car("C01", "Volkswagen", "Amarok", "SUV", 300, true);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("C01", car.getID());
        assertEquals("Volkswagen", car.getBrand());
        assertEquals("Amarok", car.getModel());
        assertEquals("SUV", car.getCategory());
        assertEquals(300, car.getPrice());
        assertTrue(car.getAvailability());
    }

    @Test
    void testSetID() {
        car.setID("C002");
        assertEquals("C002", car.getID());
    }

    @Test
    void testSetBrand() {
        car.setBrand("Ferrari");
        assertEquals("Ferrari", car.getBrand());
    }

    @Test
    void testSetModel() {
        car.setModel("F40");
        assertEquals("F40", car.getModel());
    }

    @Test
    void testSetCategory() {
        car.setCategory("Sportwagen");
        assertEquals("Sportwagen", car.getCategory());
    }

    @Test
    void testSetPrice() {
        car.setPrice(450);
        assertEquals(450, car.getPrice());
    }

    @Test
    void testSetAvailability() {
        car.setAvailability(false);
        assertFalse(car.getAvailability());
    }
}
