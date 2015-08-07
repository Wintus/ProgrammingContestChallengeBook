package Chapter3.Section5;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Master Dynamic Programming of Bit DP.
 * Created by Yuya on 2015/08/04.
 */
public class TravelingSalesmanProblem {
    private static final int INF = Integer.MAX_VALUE / 2;
    private final int N;
    private final int distance[][];
    // DP table
    private final int[][] dp;
    private final int ALL;

    public TravelingSalesmanProblem(int n, int[][] distance) {
        N = n;
        this.distance = distance;
        dp = new int[1 << N][N];
        ALL = (1 << N) - 1;
    }

    // set is the set of visited vertexes. v is the current vertex.
    private int recursion(int set, int v) {
        if (dp[set][v] >= 0)
            return dp[set][v];
        if (set == ALL && v == 0)
            // traversed all vertexes
            return dp[set][v] = 0;

        int result = INF;
        for (int u = 0; u < N; u++)
            if ((set >> u & 1) != 1)
                // go next
                result = Math.min(result,
                        recursion(set | 1 << u, u) + distance[v][u]);
        return dp[set][v] = result;
    }

    int solveRec() {
        for (int[] ints : dp) Arrays.fill(ints, -1);
        return recursion(0, 0);
    }

    int solve() {
        for (int[] ints : dp) Arrays.fill(ints, INF);
        dp[ALL][0] = 0;

        for (int set = ALL - 1; set >= 0; set--)
            for (int v = 0; v < N; v++)
                for (int u = 0; u < N; u++)
                    if ((set >> u & 1) != 1)
                        dp[set][v] = Math.min(dp[set][v],
                                dp[set | 1 << u][u] + distance[v][u]);
        return dp[0][0];
    }

    public static void main(String[] args) {
        try (Scanner ignored = new Scanner(System.in)) {
            int n = 5; // scanner.nextInt();
            int[][] d = new int[n][n];
            for (int[] edge : d) Arrays.fill(edge, INF);
            for (int i = 0; i < n; i++) d[i][i] = 0;
//            for (int[] edge : d) Arrays.setAll(edge, x -> scanner.nextInt());
            d[0][1] = 3;
            d[0][3] = 4;
            d[1][2] = 5;
            d[2][0] = 4;
            d[2][3] = 5;
            d[3][4] = 3;
            d[4][0] = 7;
            d[4][1] = 6;

            TravelingSalesmanProblem TSP = new TravelingSalesmanProblem(n, d);
            System.out.println(TSP.solveRec());
            System.out.println();
            System.out.println(TSP.solve());
        }
    }
}
