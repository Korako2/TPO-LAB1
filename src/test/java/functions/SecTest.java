package functions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class SecTest {
    Sec sec = new Sec();
    final double PI = Math.PI;

    @ParameterizedTest
    @DisplayName("Check arguments between 0 and 2*pi")
    @ValueSource(doubles = {0, 1, 2 * PI / 3, PI, 5 * PI / 4, 11 * PI / 6})
    void checkPositivePointsInFirstTurnOfCircle(double x) {
        assertAll(
                () -> assertEquals((double) 1 / Math.cos(x), sec.sec(x, 30), 0.0001)
        );
    }

    @ParameterizedTest
    @DisplayName("Check arguments between -2*pi and 0")
    @ValueSource(doubles = {-2 * PI, -PI / 3, -5 * PI / 6, -PI, -3.7, 7 * PI / 4})
    void checkNegativePointsInFirstTurnOfCircle(double x) {
        assertAll(
                () -> assertEquals((double) 1 / Math.cos(x), sec.sec(x, 30), 0.0001)
        );
    }

    @ParameterizedTest
    @DisplayName("Should throw IllegalArgumentException if x = PI/2 + PIk")
    @ValueSource(doubles = {PI / 2, -PI / 2, 3 * PI / 2, 201 * PI / 2, -1000001 * PI / 2})
    void checkPIPointsOfUncertainty(double x) {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> sec.sec(x, 30))
        );
    }
    @ParameterizedTest
    @DisplayName("Should throw IllegalArgumentException if x is no further from PI/2 + PIk than 0.0999")
    @ValueSource(doubles = {-PI / 2 - 0.0998, PI / 2 + 0.0998, PI / 2 - 0.0998, -PI / 2 + 0.0998, -PI / 2 - 0.0998999999})
    void checkPointsOfUncertainty(double x ) {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> sec.sec(x, 30))
        );
    }

    @ParameterizedTest
    @DisplayName("Check arguments whose abs is greater than 2*PI")
    @ValueSource(doubles = {4 * PI, -123 * PI / 12, 12345 * PI, -PI / 100})
    void checkArgumentsWhoseAbsGreaterDoublePI(double x) {
        assertAll(
                () -> assertEquals((double) 1 / Math.cos(x), sec.sec(x, 30), 0.0001)
        );
    }

    @ParameterizedTest
    @DisplayName("Check arguments close to uncertainty points")
    @ValueSource(doubles = {-PI / 2 + 0.0999, 101 * PI / 2 + 0.1, PI / 2 - 0.1, PI / 2 + 0.1, -PI / 2 - 0.1})
    void checkArgumentsCloseToUncertainty(double x) {
        assertAll(
                () -> assertEquals((double) 1 / Math.cos(x), sec.sec(x, 30), 0.3)
        );
    }

    @ParameterizedTest
    @DisplayName("Check the parity of the function")
    @ValueSource(doubles = {2 * PI / 3, -PI / 2 + 0.3 , 15 * PI, 201.6 * PI / 2, -1000000 * PI})
    void checkParity(double x) {
        assertAll(
                () -> assertEquals(sec.sec(x, 30), sec.sec(-x, 30), 0.0001)
        );
    }

    @ParameterizedTest
    @DisplayName("Check the period of the function")
    @ValueSource(doubles = {2 * PI / 3, -PI / 2 + 0.3 , 15 * PI, 201.6 * PI / 2, -1000000 * PI})
    void checkPeriod(double x) {
        assertAll(
                () -> assertEquals(sec.sec(x, 30), sec.sec(x + 2 * PI, 30), 0.0001)
        );
    }

}