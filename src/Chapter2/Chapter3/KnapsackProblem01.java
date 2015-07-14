package Chapter2.Chapter3;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Dynamic Programming.
 * Created by Yuya on 2015/07/13.
 */
public class KnapsackProblem01 {
    int[][] items; // {{weight, value}}
    int weight;
    int[][] dp; // {{nth item, weight}}

    public KnapsackProblem01(int[][] items, int weight) {
        this.items = items;
        this.weight = weight;
        dp = new int[items.length + 1][items[0].length + 1];
    }

    int recursion(int n, int w) {
        if (dp[n][w] >= 0)
            return dp[n][w];
        int result;
        if (n == items.length)
            result = 0;
        else if (w < items[n][0])
            result = recursion(n + 1, w);
        else
            result = Math.max(recursion(n + 1, w),
                    recursion(n + 1, w - items[n][0]) + items[n][1]);
        return dp[n][w] = result;
    }

    int solve() {
        IntStream.range(0, dp.length)
                 .forEach(n -> Arrays.setAll(dp[n], x -> -1));
        return recursion(0, weight);
    }
}
