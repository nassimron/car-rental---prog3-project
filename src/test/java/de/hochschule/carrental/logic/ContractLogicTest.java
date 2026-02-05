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

}