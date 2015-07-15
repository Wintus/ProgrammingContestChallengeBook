package Chapter2.Section3;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Dynamic Programming.
 * Created by Yuya on 2015/07/14.
 */
public class RepeatedCombination {
    int n, m;
    int[] a; // number of each n items
    int M;
    int[][] dp;

    public RepeatedCombination(int[] a, int n, int m) {
        this.n = n;
        this.a = a;
        this.m = a.length;
        M = m;
        dp = new int[m + 1][n + 1];
    }

    /**
     * dp[i+1][j] = total number of combination of j items up to ith.
     *
     * @return total number of combination of m items of n kinds.
     */
    int solve() {
        IntStream.rangeClosed(0, m).forEach(i -> dp[i][0] = 1);
        // @formatter:off
        IntStream.range(0, m).forEach(i ->
            IntStream.rangeClosed(1, n).forEach(j ->
                dp[i + 1][j] = (dp[i + 1][j - 1] + dp[i][j] +
                    (j - 1 - a[i] >= 0 ? M - dp[i][j - 1 - a[i]] : 0)) % M));
        return dp[m][n];
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int[] a = new int[n];
            Arrays.setAll(a, x -> scanner.nextInt());
            int M = scanner.nextInt();
            System.out.println(new RepeatedCombination(a, m, M).solve());
        }
    }
}
