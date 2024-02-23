package functions;

import java.math.BigDecimal;

public class Sec {
    public double sec(double x) {
        System.out.println(calcBinomialCoefficient(10,  5));
        return 1 / Math.cos(x);
    }

    private BigDecimal calcBinomialCoefficient(int n, int k) {
        return calcFactorial(n).divide(calcFactorial(k).multiply(calcFactorial(n - k)));

    }

    private BigDecimal calcFactorial(int n) {
        BigDecimal factorial = BigDecimal.ONE;
        for (int i = 1; i <= n; i++) {
            factorial = factorial.multiply(BigDecimal.valueOf(i));
        }
        return factorial;
    }
}
