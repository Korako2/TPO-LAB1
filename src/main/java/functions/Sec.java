package functions;

import util.MathUtils;

public class Sec {
    MathUtils mathUtils = new MathUtils();

    public double sec(double x, int n) throws IllegalArgumentException {
        if (n > 30) throw new IllegalArgumentException("The decomposition order is too large");
        double sec = 0;
        x = bringXtoFirstTurnOfCircle(x);
        if (Math.abs(x) > Math.PI / 2) {
            if (x > 0) x = Math.PI - x;
            else x = -Math.PI - x;
        }
        double eps =  1e-5;
        if (Math.abs(x - Math.PI / 2) < eps || Math.abs(x + Math.PI / 2) < eps) throw new IllegalArgumentException("The function is not defined for this value");
        double factorial = 1;
        int t = 1;
        for (int k = 0; k < n; k++) {
            if (k != 0) {
                factorial /= t;
                factorial /= t + 1;
                t += 2;
            }
            double E = mathUtils.calcEulerNumber(2 * k);
            sec += Math.pow(-1, k) * E * Math.pow(x, 2 * k) * factorial;
        }
        return sec;
    }


    private double bringXtoFirstTurnOfCircle(double x) {
        double doublePI = 2 * Math.PI;
        while (x > Math.PI)
            x = x - doublePI;
        while (x < -Math.PI)
            x = x + doublePI;
        return x;
    }


}
