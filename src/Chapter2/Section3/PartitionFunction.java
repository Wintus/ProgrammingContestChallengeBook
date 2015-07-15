package Chapter2.Section3;

import java.util.stream.IntStream;

/**
 * Dynamic Programming.Partial Function of Mod M.
 * Created by Yuya on 2015/07/14.
 */
public class PartitionFunction {
    int n, m, M;
    int[][] dp;

    public PartitionFunction(int n, int m, int m1) {
        this.n = n;
        this.m = m;
        M = m1;
        dp = new int[n][m];
    }

    /**
     * dp[i][j] = total number of i partition of j.
     *
     * @return mod m of m partition of n.
     */
    int solve() {
        IntStream.rangeClosed(1, m).forEach(i ->
                IntStream.rangeClosed(0, n).forEach(j ->
                        dp[i][j] = j - i >= 0 ?
                                dp[i - 1][j] + dp[i][j - i] % M :
                                dp[i - 1][j]));
        return dp[m][n];
    }
}
