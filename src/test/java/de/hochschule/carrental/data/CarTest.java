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
    void testSetters() {

        car.setID("C002");
        car.setBrand("Ferrari");
        car.setModel("F40");
        car.setCategory("Sportwagen");
        car.setPrice(450);
        car.setAvailability(false);

        assertEquals("C002", car.getID());
        assertEquals("Ferrari", car.getBrand());
        assertEquals("F40", car.getModel());
        assertEquals("Sportwagen", car.getCategory());
        assertEquals(450, car.getPrice());
        assertFalse(car.getAvailability());
    }
}