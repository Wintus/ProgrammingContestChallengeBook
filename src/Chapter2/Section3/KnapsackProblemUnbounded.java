package Chapter2.Section3;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Dynamic Programming.Knapsack Problem with no limit in number of each items.
 * Created by Yuya on 2015/07/13.
 */
class KnapsackProblemUnbounded {
    private final int[][] items; // {{weight, value}}
    private final int weight;
    private final int[][] dp; // {{nth item, weight}}

    public KnapsackProblemUnbounded(int[][] items, int weight) {
        this.items = items;
        this.weight = weight;
        dp = new int[items.length + 1][weight + 1];
    }

    /**
     * iterative version solver in ascending order of n.
     *
     * @return max total value.
     */
    int solve() {
        int N = items.length;
        Arrays.setAll(dp[0], x -> 0);
        // @formatter:off
        IntStream.range(0, N).forEach(n ->
            IntStream.rangeClosed(0, weight).forEach(w ->
                dp[n + 1][w] = w < items[n][0] ? dp[n][w] :
                    Math.max(dp[n][w],
                             dp[n + 1][w - items[n][0]] + items[n][1])));
        return dp[N][weight];
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int[][] items = new int[n][2];
            IntStream.range(0, n).forEach(i ->
                    Arrays.setAll(items[i], x -> scanner.nextInt()));
            int w = scanner.nextInt();
            System.out.println(new KnapsackProblemUnbounded(items, w).solve());
        }
    }
}
