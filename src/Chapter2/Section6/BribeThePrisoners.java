package Chapter2.Section6;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Try GCJ Part 1.
 * Created by Yuya on 2015/07/24.
 */
class BribeThePrisoners {
    private final int P, Q;
    private final int[] A;
    private final int[][] dp; // cost to free prisoners in (i, j)

    public BribeThePrisoners(int p, int[] a) {
        P = p;
        Q = a.length;
        A = new int[Q + 2];
        System.arraycopy(a, 0, A, 1, Q);
        dp = new int[Q + 1][Q + 2];
    }

    int solve() {
        A[0] = 0;
        A[Q + 1] = P + 1;
        for (int q = 0; q < Q; q++) dp[q][q + 1] = 0;
        // fill dp from smaller bound
        for (int w = 2; w <= Q + 1; w++) {
            for (int i = 0; w + i <= Q + 1; i++) {
                // calc dp[i][j]
                int j = w + i, t = Integer.MAX_VALUE;
                // find the min cost by brut forth
                for (int k = i + 1; k < j; k++)
                    t = Math.min(t, dp[i][k] + dp[k][j]);
                // always cost this at first without t
                dp[i][j] = t + A[j] - A[i] - 2;
            }
        }
        return dp[0][Q + 1];
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int p = scanner.nextInt();
            int q = scanner.nextInt();
            int[] a = new int[q];
            Arrays.setAll(a, x -> scanner.nextInt());
            BribeThePrisoners bp = new BribeThePrisoners(p, a);
            System.out.println(bp.solve());
        }
    }
}
