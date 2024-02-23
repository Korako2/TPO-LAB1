package util;

import java.math.BigDecimal;
import java.math.BigInteger;

public class MathUtils {
    public double calcBinomialCoefficient(int n, int k) {
        return calcFactorial(n) / (calcFactorial(k) * calcFactorial(n - k));
    }

    public long calcFactorial(int n) {
        if (n < 0) throw new IllegalArgumentException("n must be non-negative");
        if (n > 20) throw new ArithmeticException("n is too large");
        long factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        return factorial;
    }
}
