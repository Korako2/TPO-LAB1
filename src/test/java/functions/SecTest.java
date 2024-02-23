package functions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class SecTest {
    Sec sec = new Sec();
    @ParameterizedTest
    @DisplayName("Check arguments between -pi/2 and pi/2")
    @ValueSource(doubles = {-Math.PI / 3, Math.PI / 4, 0, 1, -1})
    void check(double x) {
        assertAll(
                () -> assertEquals((double) 1 / Math.cos(x), sec.sec(x, 30), 0.0001)
        );
    }

    @ParameterizedTest
    @DisplayName("Should throw IllegalArgumentException if x = PI/2 + PIk")
    @ValueSource(doubles = {Math.PI / 2, -Math.PI / 2, 3 * Math.PI / 2, 201 * Math.PI / 2, -1000001 * Math.PI / 2})
    void checkPointsOfUncertainty(double x) {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> sec.sec(x, 30))
        );
    }
}