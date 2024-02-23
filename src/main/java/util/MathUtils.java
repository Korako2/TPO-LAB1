package util;
import org.apache.commons.math3.util.CombinatoricsUtils;

import java.util.ArrayList;

public class MathUtils {
    double[] bernoulliNumbers;

    public long calcFactorial(int n) throws IllegalArgumentException, ArithmeticException {
        if (n < 0) throw new IllegalArgumentException("n must be non-negative");
        if (n > 20) throw new ArithmeticException("n is too large");
        long factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        return factorial;
    }

    public void calcBernoulliNumbers() throws IllegalArgumentException {
        bernoulliNumbers = new double[62];
        bernoulliNumbers[0] = 1;
        bernoulliNumbers[1] = -0.5;
        double B;
        for (int i = 2; i <= 60; i += 2) {
            B = 0;
            for (int k = 1; k <= i; k ++) {
                B += CombinatoricsUtils.binomialCoefficient(i + 1, k + 1) * bernoulliNumbers[i - k];
            }
            B *= -1.0 / (i + 1);
            bernoulliNumbers[i] = B;
            bernoulliNumbers[i + 1] = 0;
        }
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
