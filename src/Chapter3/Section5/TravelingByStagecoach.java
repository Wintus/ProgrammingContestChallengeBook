package Chapter3.Section5;

import Library.ArrayTools;

/**
 * Master Dynamic Programming. DAG.
 * Created by Yuya on 2015/08/06.
 */
public class TravelingByStagecoach {
    private final int N, M, start, goal;
    private final int[] horse;
    private final int[][] distance;
    // Bit DP of minimum costs to be at v with remaining ticket set
    private final double[][] dp;
    private static final double INF = Double.MAX_VALUE / 2;

    public TravelingByStagecoach(int m, int start, int goal, int[] horse, int[][] dis) {
        N = horse.length; // number of tickets
        M = m; // number of cities
        this.start = start - 1; // start, indexed
        this.goal = goal - 1; // goal, indexed
        this.horse = horse; // numbers of horses
        distance = dis; // -1 if no path
        dp = new double[1 << N][M];
    }

    double solve() {
        ArrayTools.fill2d(dp, INF);
        dp[dp.length - 1][start] = 0;
        double result = INF;
        for (int set = (1 << N) - 1; set >= 0; set--) {
            result = Math.min(result, dp[set][goal]);
            for (int v = 0; v < M; v++)
                for (int i = 0; i < N; i++)
                    if ((set >> i & 1) == 1)
                        for (int u = 0; u < M; u++)
                            // move from v to u by horse i
                            if (distance[v][u] >= 0)
                                dp[set & ~(1 << i)][u] = Math.min(
                                        dp[set & ~(1 << i)][u],
                                        dp[set][v] + (double) distance[v][u] / horse[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 2, m = 4, a = 2, b = 1;
        int[] t = new int[]{3, 1};
        int[][] d = new int[m][m];
        ArrayTools.fill2d(d, -1);
        d[0][2] = 3;
        d[0][3] = 2;
        d[1][2] = 3;
        d[1][3] = 5;
        d[2][0] = 3;
        d[2][1] = 3;
        d[3][0] = 2;
        d[3][1] = 5;

        double result = new TravelingByStagecoach(m, a, b, t, d).solve();

        if (result == TravelingByStagecoach.INF) // unreachable
            System.out.println("Impossible");
        else
            System.out.printf("%.3f\n", result);
    }
}
