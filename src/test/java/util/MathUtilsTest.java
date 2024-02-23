package util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathUtilsTest {

    private MathUtils mathUtils = new MathUtils();
    @Test
    void calcBinomialCoefficient() {

    }
    @Test
    @DisplayName("Calculate factorial")
    void calcFactorial() {
        assertAll(
                () -> assertEquals(1, mathUtils.calcFactorial(0)),
                () -> assertEquals(1, mathUtils.calcFactorial(1)),
                () -> assertEquals(3628800, mathUtils.calcFactorial(10)),
                () -> assertEquals(2432902008176640000L, mathUtils.calcFactorial(20))
        );
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException if n is negative")
    void calcFactorialNegative() {
        assertThrows(IllegalArgumentException.class, () -> mathUtils.calcFactorial(-1));
    }

    @Test
    @DisplayName("Should throw ArithmeticException if n is too large")
    void calcFactorialTooLarge() {
        assertThrows(ArithmeticException.class, () -> mathUtils.calcFactorial(21));
    }
}