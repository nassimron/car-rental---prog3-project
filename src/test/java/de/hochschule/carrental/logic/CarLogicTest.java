package de.hochschule.carrental.logic;

import de.hochschule.carrental.data.Database;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;


public class CarLogicTest {

    @BeforeEach
    void prepareDatabase() throws Exception {
        System.setProperty("db_url","jdbc:sqlite:data/test.db");

        try (Connection conn = Database.connect();
             Statement stmt = conn.createStatement()) {

            stmt.execute("""
                        CREATE TABLE IF NOT EXISTS cars(
                            id TEXT PRIMARY KEY,
                            brand TEXT,
                            model TEXT,
                            category TEXT,
                            price INTEGER,
                            available BOOLEAN
                        )
                    """);

            stmt.execute("DELETE FROM cars");

            stmt.execute("INSERT INTO cars VALUES('A1','BMW','320','Limousine',100,true)");
            stmt.execute("INSERT INTO cars VALUES('A2','Audi','A3','Limousine',80,false)");
            stmt.execute("INSERT INTO cars VALUES('A5','VW','Golf','Small',70,true)");
        }
    }


    @Test
    void testLoadAndGetAllCars() {
        CarLogic logic = new CarLogic();
        assertEquals(3, logic.getAllCars().size());
    }


    @Test
    void testGetCarById() {
        CarLogic logic = new CarLogic();
        var car = logic.getCarById("A1");

        assertNotNull(car);
        assertEquals("BMW", car.getBrand());
        assertEquals("320", car.getModel());
    }


    @Test
    void testGetCarByIdNotFound() {
        CarLogic logic = new CarLogic();
        var car = logic.getCarById("A99");
        assertNull(car);
    }


    @Test
    void testGetAvailableCars() {
        CarLogic logic = new CarLogic();
        var list = logic.getAvailableCars();
        assertEquals(2, list.size());
    }


    @Test
    void testGetNextCarNumber() {
        CarLogic logic = new CarLogic();
        int next = logic.getNextCarNumber();
        assertEquals(6, next);
    }


    @Test
    void testCreateCar() {
        CarLogic logic = new CarLogic();

        var car = logic.create("Mercedes", "C200", "Limousine", 120, true);

        assertNotNull(car);
        assertEquals("A6", car.getID());
        assertEquals(4, logic.getAllCars().size());
    }


    @Test
    void testCreateAndFindCar() {
        CarLogic logic = new CarLogic();

        logic.create("Opel", "Corsa", "Small", 50, true);

        var car = logic.getCarById("A6");
        assertNotNull(car);
        assertEquals("Opel", car.getBrand());
    }


    @Test
    void testUpdateAvailability() {
        CarLogic logic = new CarLogic();

        var car = logic.getCarById("A2");
        assertFalse(car.getAvailability());

        logic.updateAvailability("A2", true);

        var updated = logic.getCarById("A2");
        assertTrue(updated.getAvailability());
    }


    @Test
    void testCreateMultipleCars() {

        CarLogic logic = new CarLogic();

        var c1 = logic.create("Ford", "Fiesta", "Small", 40, true);
        var c2 = logic.create("Seat", "Ibiza", "Small", 45, false);

        assertEquals("A6", c1.getID());
        assertEquals("A7", c2.getID());
    }

}
