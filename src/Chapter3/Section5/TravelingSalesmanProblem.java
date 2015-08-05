package Chapter3.Section5;

import java.util.Arrays;

/**
 * Master Dynamic Programming of Bit DP.
 * Created by Yuya on 2015/08/04.
 */
public class TravelingSalesmanProblem {
    private final int N;
    private final int distance[][];
    // DP table
    private int[][] dp;

    public TravelingSalesmanProblem(int n, int[][] distance) {
        N = n;
        this.distance = distance;
        dp = new int[1 << N][N];
    }

    // set is the set of visited vertexes. v is the current vertex.
    private int recursion(int set, int v) {
        if (dp[set][v] >= 0)
            return dp[set][v];
        if (set == (1 << N) - 1 && v == 0)
            // traversed all vertexes
            return dp[set][v] = 0;

        int result = Integer.MAX_VALUE;
        for (int u = 0; u < N; u++) {
            if (!((set >> u & 1) == 1))
                // go next
                result = Math.min(result,
                        recursion(set | 1 << u, u) + distance[v][u]);
        }
        return dp[set][v] = result;
    }

    int solveRec() {
        for (int[] ints : dp) Arrays.fill(ints, -1);
        return recursion(0, 0);
    }
}
