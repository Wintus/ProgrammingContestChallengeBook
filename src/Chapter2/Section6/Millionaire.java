package Chapter2.Section6;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Try GCJ Part 1.
 * Created by Yuya on 2015/07/24.
 */
public class Millionaire {
    private final int M, X;
    private final double P;
    private final double[][] dp;

    public Millionaire(int m, double p, int x) {
        M = m;
        P = p;
        X = x;
        dp = new double[2][(1 << M) + 1];
    }

    double solve() {
        int n = 1 << M;
        double[] previous = dp[0], next = dp[1];
        Arrays.fill(previous, 0.0);
        previous[n] = 1.0;

        for (int r = 0; r < M; r++) {
            for (int i = 0; i <= n; i++) {
                double t = 0.0;
                try {
                    for (int j = 0; j <= n; j++) {
                        t = Math.max(t, P * previous[i + j]
                                + (1 - P) * previous[i - j]);
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
                next[i] = t;
            }
            // swap
            double[] dummy = previous;
            previous = next;
            next = dummy;
        }
        return previous[n * X / 1_000_000];
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int m = scanner.nextInt();
            double p = scanner.nextDouble();
            int x = scanner.nextInt();
            Millionaire millionaire = new Millionaire(m, p, x);
            System.out.printf("%.6f\n", millionaire.solve());
        }
    }
}
