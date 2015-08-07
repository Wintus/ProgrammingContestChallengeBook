package Chapter3.Section5;

import java.util.Arrays;

/**
 * Master Dynamic Programming. DAG.
 * Created by Yuya on 2015/08/06.
 */
public class TravelingByStagecoach {
    private final int N, M, A, B;
    private final int[] horse;
    private final int[][] distance;
    // Bit DP of minimum costs to be at v with remaining tickets S
    private final double[][] dp;
    private static final double INF = Double.MAX_VALUE / 2;

    public TravelingByStagecoach(int n, int m, int a, int b, int[] horse) {
        N = n; // number of tickets
        M = m; // number of roads
        A = a; // start
        B = b; // goal
        this.horse = horse; // numbers of horses
        distance = new int[M][M]; // -1 if no path
        dp = new double[1 << N][M];
    }

    double solve() {
        for (double[] doubles : dp) Arrays.fill(doubles, INF);
        dp[dp.length - 1][A - 1] = 0;
        double result = INF;
        for (int set = (1 << N) - 1; set >= 0; set--) {
            result = Math.min(result, (int) dp[set][B - 1]);
            for (int v = 0; v < M; v++)
                for (int i = 0; i < N; i++)
                    if ((set >> i & 1) == 1)
                        for (int u = 0; u < M; u++)
                            // move from v to u by horse i
                            if (distance[v][u] >= 0)
                                dp[set & ~(1 << i)][u] = Math.min(
                                        dp[set & ~(1 << i)][u],
                                        dp[set][v] + (double)
                                                distance[v][u] / horse[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 2, m = 4, a = 2, b = 1;
        int[] t = {3, 1};
        int[][] d = new int[m][m];
        for (int[] ints : d) Arrays.fill(ints, -1);
        d[0][2] = 3;
        d[0][3] = 2;
        d[1][2] = 3;
        d[1][3] = 5;
        d[2][0] = 3;
        d[2][1] = 3;
        d[3][0] = 2;
        d[3][1] = 5;

        double result = new TravelingByStagecoach(n, m, a, b, t).solve();

        if (result == TravelingByStagecoach.INF) // unreachable
            System.out.println("Impossible");
        else
            System.out.printf("%.3f\n", result);
    }
}
