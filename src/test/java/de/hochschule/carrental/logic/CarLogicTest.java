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
    void getCarById_nonExisting_returnsNull() {
        assertNull(carLogic.getCarById("does-not-exist"));
    }

}
