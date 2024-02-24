package util;

import org.apache.commons.math3.util.CombinatoricsUtils;

public class MathUtils {

    public long calcFactorial(int n) throws IllegalArgumentException, ArithmeticException {
        if (n < 0) throw new IllegalArgumentException("n must be non-negative");
        if (n > 20) throw new ArithmeticException("n is too large");
        long factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        return factorial;
    }

    public double calcEulerNumber(int m) {
        if (m < 0) throw new IllegalArgumentException("n must be non-negative");
        if (m > 60) throw new IllegalArgumentException("n is too large");
        if (m == 0) return 1;
        if (m % 2 != 0) return 0;
        int n = m / 2;
        double E = 0;
        for (int k = 0; k <= n - 1; k++) {
            E += CombinatoricsUtils.binomialCoefficient(2 * n, 2 * k) * calcEulerNumber(2 * k);
        }
        E *= -1;
        return E;
    }
}
