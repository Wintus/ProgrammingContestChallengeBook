package Chapter2.Section6;

import java.util.*;

/**
 * Try GCJ Part 1.
 * Created by Yuya on 2015/07/24.
 */
class BribeThePrisoners {
    private final int P, Q;
    private final int[] A;
    private final int[][] dp; // cost to free prisoners in (i, j)
    private Map<String, Integer> memory;

    public BribeThePrisoners(int p, int[] a) {
        P = p;
        Q = a.length;
        A = a;
        dp = new int[Q + 1][Q + 2];
    }

    int solve() {
        int[] A = new int[Q + 2];
        System.arraycopy(this.A, 0, A, 1, Q);
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

    // return cost to release prisoners in [leftBorder, rightBorder]
    private int _solve(int leftBorder, int rightBorder, List<Integer> releases) {
        String key = leftBorder + "-" + rightBorder + ":" + releases.toString();
        if (memory.containsKey(key)) return memory.get(key);
        int n = rightBorder - leftBorder + 1;
        if (releases.size() == 1) return n - 1; // pay for the rest
        if (releases.size() == 0) return 0;
        // try every prisoner
        int cost = Integer.MAX_VALUE;
        for (int i = 0; i < releases.size(); i++) {
            ArrayList<Integer> left = new ArrayList<>();
            ArrayList<Integer> right = new ArrayList<>();
            left.addAll(releases.subList(0, i));
            right.addAll(releases.subList(i + 1, releases.size()));
            int _cost = n - 1 +
                    _solve(leftBorder, releases.get(i) - 1, left) +
                    _solve(releases.get(i) + 1, rightBorder, right);
            cost = Math.min(cost, _cost);
        }
        memory.put(key, cost);
        return cost;
    }

    /**
     * <a href="http://www.algorithmatica.com/bribe-the-prisoners/">source</a>
     *
     * @return cost to release prisoners.
     */
    int solve1() {
        memory = new HashMap<>();
        ArrayList<Integer> releases = new ArrayList<>();
        for (int a : A) releases.add(a);
        return _solve(1, P, releases);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int p = scanner.nextInt();
            int q = scanner.nextInt();
            int[] a = new int[q];
            Arrays.setAll(a, x -> scanner.nextInt());
            BribeThePrisoners bp = new BribeThePrisoners(p, a);
            System.out.println(bp.solve());
            System.out.println(bp.solve1());
        }
    }
}
