package de.hochschule.carrental.logic;

import de.hochschule.carrental.data.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarLogicTest {

    private CarLogic carLogic;

    @BeforeEach
    void setUp() {
        carLogic = new CarLogic();
    }

    @Test
    void create_validInput_addsCarAndReturnsIt() {
        Car car = carLogic.create("1", "BMW", "320i", "Limousine", 100, true);

        assertNotNull(car);
        assertEquals("1", car.getID());
        assertEquals(1, carLogic.getAllCars().size());
        assertSame(car, carLogic.getCarById("1"));
    }

    @Test
    void create_nullId_throwsIllegalArgumentException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> carLogic.create(null, "BMW", "320i", "Limousine", 100, true));

        assertEquals("Car ID must not be empty", ex.getMessage());
    }

    @Test
    void create_blankId_throwsIllegalArgumentException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> carLogic.create("   ", "BMW", "320i", "Limousine", 100, true));

        assertEquals("Car ID must not be empty", ex.getMessage());
    }

    @Test
    void create_nullBrand_throwsIllegalArgumentException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> carLogic.create("1", null, "320i", "Limousine", 100, true));

        assertEquals("Brand must not be empty", ex.getMessage());
    }

    @Test
    void create_blankBrand_throwsIllegalArgumentException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> carLogic.create("1", "   ", "320i", "Limousine", 100, true));

        assertEquals("Brand must not be empty", ex.getMessage());
    }

    @Test
    void create_nullModel_throwsIllegalArgumentException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> carLogic.create("1", "BMW", null, "Limousine", 100, true));

        assertEquals("Model must not be empty", ex.getMessage());
    }

    @Test
    void create_blankModel_throwsIllegalArgumentException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> carLogic.create("1", "BMW", "   ", "Limousine", 100, true));

        assertEquals("Model must not be empty", ex.getMessage());
    }

    @Test
    void create_priceZero_throwsIllegalArgumentException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> carLogic.create("1", "BMW", "320i", "Limousine", 0, true));

        assertEquals("Price must be greater than 0", ex.getMessage());
    }

    @Test
    void create_priceNegative_throwsIllegalArgumentException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> carLogic.create("1", "BMW", "320i", "Limousine", -5, true));

        assertEquals("Price must be greater than 0", ex.getMessage());
    }

    @Test
    void create_duplicateId_throwsIllegalStateException() {
        carLogic.create("1", "BMW", "320i", "Limousine", 100, true);

        IllegalStateException ex = assertThrows(IllegalStateException.class,
                () -> carLogic.create("1", "Audi", "A4", "Kombi", 120, true));

        assertEquals("Car with ID 1 already exists", ex.getMessage());
        assertEquals(1, carLogic.getAllCars().size());
    }

    @Test
    void getCarById_nonExisting_returnsNull() {
        assertNull(carLogic.getCarById("does-not-exist"));
    }

    @Test
    void delete_existingCar_removesIt() {
        carLogic.create("1", "BMW", "320i", "Limousine", 100, true);
        carLogic.create("2", "Audi", "A4", "Kombi", 120, true);


        carLogic.delete("1");

        assertNull(carLogic.getCarById("1"));
        assertNotNull(carLogic.getCarById("2"));
        assertEquals(1, carLogic.getAllCars().size());
    }

    @Test
    void delete_nonExisting_doesNothing() {
        carLogic.create("1", "BMW", "320i", "Limousine", 100, true);

        carLogic.delete("999");

        assertEquals(1, carLogic.getAllCars().size());
        assertNotNull(carLogic.getCarById("1"));
    }

    @Test
    void getAllCars_returnsInternalList_referenceBehavior() {
        carLogic.create("1", "BMW", "320i", "Limousine", 100, true);

        List<Car> list = carLogic.getAllCars();
        assertEquals(1, list.size());

        list.clear();
        assertEquals(0, carLogic.getAllCars().size());
    }
}
