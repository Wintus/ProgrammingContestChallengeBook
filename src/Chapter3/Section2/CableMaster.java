package Chapter3.Section2;

import java.util.Arrays;
import java.util.Scanner;

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

    double solve() {
        return Math.floor(
                LowerBound.lowerBound(this::condition, .01) * 100) / 100;
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
