package Chapter3.Section5;

import Library.RangeMinimumQuery;

import java.util.Arrays;
import java.util.Scanner;

/**
 * DP with RMQ.
 * Created by Yuya on 2015/08/12.
 */
public class MinimizingMaximizer {
    private final int N;
    private final int[][] sortRanges;
    private final int[] dp;
    private final RangeMinimumQuery RMQ;

    public MinimizingMaximizer(int n, int[][] sortRanges) {
        N = n;
        this.sortRanges = sortRanges;
        dp = new int[N];
        RMQ = new RangeMinimumQuery(N);
    }

    int solve() {
        RMQ.initialize();
        Arrays.fill(dp, RangeMinimumQuery.maxInt);
        // by index
        dp[0] = 0;
        RMQ.update(0, 0);
        for (int[] range : sortRanges) {
            final int s = range[0] - 1, t = range[1] - 1; // to index
            int v = Math.min(dp[t], RMQ.query(s, t + 1) + 1); // no change or proceeded
            dp[t] = v;
            RMQ.update(t, v);
        }
        return dp[N - 1]; // to position
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
//            int n = scanner.nextInt();
            int n = 40, m = 6;
            int[][] ranges = {{20, 30}, {1, 10}, {10, 20}, {20, 30}, {15, 25}, {30, 40}};
            System.out.println(new MinimizingMaximizer(n, ranges).solve());
        }
    }
}
