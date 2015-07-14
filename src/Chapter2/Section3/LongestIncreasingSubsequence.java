package Chapter2.Section3;

import java.util.stream.IntStream;

/**
 * Dynamic Programming.
 * Created by Yuya on 2015/07/14.
 */
class LongestIncreasingSubsequence {
    int[] numbers;
    int[] dp;

    public LongestIncreasingSubsequence(int[] numbers, int[] dp) {
        this.numbers = numbers;
        this.dp = dp;
    }

    /**
     * dp[i] = length of LIS that its last one is numbers[i].
     *
     * @return length of LIS.
     */
    int solve() {
        return IntStream.range(0, numbers.length).map(i -> {
            dp[i] = 1;
            IntStream.range(0, i).forEach(j -> {
                if (numbers[j] < numbers[i])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            });
            return dp[i];
        }).max().getAsInt();
    }
}
