package Chapter3.Section2;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * Binary Search.
 * Created by Yuya on 2015/07/26.
 */
public class CableMaster {
    private int K;
    private double[] L;

    public CableMaster(int k, double[] l) {
        K = k;
        L = l;
    }

    boolean condition(double x) {
        int n = Arrays.stream(L).mapToInt(cable -> (int) (cable / x)).sum();
        return n >= K;
    }

    static double lowerBound(Predicate<Double> predicate) {
        double lb = 0, mid = 0, ub = 100_000;
        while (ub - lb > .01) {
            mid = (lb + ub) / 2;
            if (predicate.test(mid))
                lb = mid;
            else
                ub = mid;
        }
        return mid;
    }

    double solve() {
        return Math.floor(lowerBound(this::condition) * 100) / 100;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            double[] l = new double[n];
            Arrays.setAll(l, x -> scanner.nextDouble());
            System.out.printf("%.2f\n", new CableMaster(k, l).solve());
        }
    }
}
