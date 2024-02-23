import functions.Sec;
import org.apache.commons.math3.util.CombinatoricsUtils;
import util.MathUtils;

public class Main {
    public static void main(String[] args) {
        Sec sec = new Sec();
        MathUtils mathUtils = new MathUtils();
        long binomialCoefficient = CombinatoricsUtils.binomialCoefficient(50, 20);
        System.out.println(binomialCoefficient);

    }
}