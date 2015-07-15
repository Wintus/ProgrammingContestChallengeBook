package Chapter2.Section3;

import java.util.stream.IntStream;

/**
 * Dynamic Programming.
 * Created by Yuya on 2015/07/14.
 */
public class RepeatedCombination {
    int n, m;
    int[] a;
    int M;
    int[][] dp;

    public RepeatedCombination(int n, int[] a, int m) {
        this.n = n;
        this.a = a;
        this.m = a.length;
        M = m;
        dp = new int[m + 1][n + 1];
    }

    int solve() {
        IntStream.rangeClosed(0, m).forEach(i -> dp[i][0] = 1);
        // @formatter:off
        IntStream.range(0, m).forEach(i ->
            IntStream.rangeClosed(1, n).forEach(j ->
                dp[i + 1][j] = (dp[i + 1][j - 1] + dp[i][j] +
                    (j - 1 - a[i] >= 0 ? M - dp[i][j - 1 - a[i]] : 0)) % M));
        return dp[m][n];
    }
}
