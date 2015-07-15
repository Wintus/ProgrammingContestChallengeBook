package Chapter2.Section3;

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
}
