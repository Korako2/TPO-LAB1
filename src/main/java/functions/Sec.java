package functions;

public class Sec {
    public double sec(double x) {
        System.out.println(calcBinomialCoefficient(10,  5));
        return 1 / Math.cos(x);
    }

    private double calcBinomialCoefficient(int n, int k) {
        return factorial(n) / (factorial(k) * factorial(n - k));
    }

}
