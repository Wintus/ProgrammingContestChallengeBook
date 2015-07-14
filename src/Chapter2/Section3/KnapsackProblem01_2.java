package Chapter2.Section3;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Dynamic Programming. Knapsack Problem with a smaller bound about value.
 * Created by Yuya on 2015/07/13.
 */
class KnapsackProblem01_2 {
    private final int[][] items; // {{weight, value}}
    private final int weight;
    private final int[][] dp; // {{(nth item, value): weight}}
    private int N;
    private int V;

    public KnapsackProblem01_2(int[][] items, int weight) {
        this.items = items;
        this.weight = weight;
        N = items.length;
        V = IntStream.range(0, N).map(n -> items[n][1]).max().getAsInt();
        dp = new int[N + 1][N * V + 1];
    }

    /**
     * iterative version solver in ascending order of n.
     * dp[n + 1][v] = min total weight
     *                  of items from 0th to nth with total value = v.
     *
     * @return max total value.
     */
    int solve() {
        Arrays.setAll(dp[0], x -> Integer.MAX_VALUE / 2);
        dp[0][0] = 0;
        // @formatter:off
        IntStream.range(0, N).forEach(n ->
            IntStream.rangeClosed(0, N * V).forEach(v ->
                dp[n + 1][v] = v < items[n][1] ?
                    dp[n][v] :
                    Math.min(dp[n][v], dp[n][v - items[n][1]] + items[n][0])));
        return dp[N][weight];
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int[][] items = new int[n][2];
            // @formatter:off
            IntStream.range(0, n).forEach(i ->
                Arrays.setAll(items[i], x -> scanner.nextInt()));
            int w = scanner.nextInt();
            System.out.println(new KnapsackProblem01_2(items, w).solve());
        }
    }
}
