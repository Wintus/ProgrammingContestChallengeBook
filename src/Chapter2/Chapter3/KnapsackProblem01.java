package Chapter2.Chapter3;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Dynamic Programming.
 * Created by Yuya on 2015/07/13.
 */
class KnapsackProblem01 {
    private final int[][] items; // {{weight, value}}
    private final int weight;
    private final int[][] dp; // {{nth item, weight}}

    public KnapsackProblem01(int[][] items, int weight) {
        this.items = items;
        this.weight = weight;
        dp = new int[items.length + 1][weight + 1];
    }

    /**
     * helper function.gives max total value from nth item with total weight
     * less than w.
     *
     * @param n index of items.
     * @param w limit weight.
     * @return max total value.
     */
    private int recursion(int n, int w) {
        if (dp[n][w] >= 0)
            return dp[n][w];
        int result;
        if (n == items.length)
            result = 0;
        else if (w < items[n][0])
            result = recursion(n + 1, w);
        else // not use nth item or use it
            result = Math.max(recursion(n + 1, w),
                    recursion(n + 1, w - items[n][0]) + items[n][1]);
        return dp[n][w] = result;
    }

    /**
     * recursive version solver.
     *
     * @return max total value.
     */
    int solve() {
        IntStream.range(0, dp.length)
                 .forEach(n -> Arrays.setAll(dp[n], x -> -1));
        return recursion(0, weight);
    }

    /**
     * iterative version solver from recursive version.
     *
     * @return max total value.
     */
    int solve0() {
        int N = items.length;
        // @formatter:off
        IntStream.iterate(N - 1, n -> n - 1).limit(N).forEach(n ->
            IntStream.rangeClosed(0, weight).forEach(w ->
                dp[n][w] = w < items[n][0] ?
                    dp[n + 1][w] :
                    Math.max(dp[n + 1][w],
                             dp[n + 1][w - items[n][0]] + items[n][1])));
        return dp[0][weight];
    }

    /**
     * iterative version solver in ascending order of n.
     *
     * @return max total value.
     */
    int solve1() {
        int N = items.length;
        // @formatter:off
        IntStream.range(0, N).forEach(n ->
            IntStream.rangeClosed(0, weight).forEach(w ->
                dp[n+1][w] = w < items[n][0] ?
                    dp[n][w] :
                    Math.max(dp[n][w], dp[n][w - items[n][0]] + items[n][1])));
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
            System.out.println(new KnapsackProblem01(items, w).solve());
            System.out.println(new KnapsackProblem01(items, w).solve0());
            System.out.println(new KnapsackProblem01(items, w).solve1());
        }
    }
}
