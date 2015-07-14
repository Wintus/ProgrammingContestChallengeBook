package Chapter2.Section3;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Dynamic Programming.Knapsack Problem with no limit in number of each numbers.
 * Created by Yuya on 2015/07/13.
 */
class SubsetSumLimited {
    private final int[][] numbers; // {{value, number}}
    private final int sum;
    private final int[] dp; // {ith: remainder}

    public SubsetSumLimited(int[][] numbers, int sum) {
        this.numbers = numbers;
        this.sum = sum;
        dp = new int[sum + 1];
    }

    /**
     * iterative version solver reusing the same array.
     * dp[i+1][j] = max remainder of ith value
     * to make j by using values from 0th to ith.
     *
     * @return max total value.
     */
    int solve() {
        Arrays.setAll(dp, x -> -1);
        dp[0] = 0;
        // @formatter:off
        IntStream.range(0, numbers.length).forEach(n ->
            IntStream.rangeClosed(0, sum).forEach(v -> {
                if (dp[v] >= 0)
                    dp[v] = numbers[n][1];
                else if (v < numbers[n][0] || dp[v - numbers[n][0]] <= 0)
                    dp[v] = -1;
                else
                    dp[v] = dp[v - numbers[n][0]] - 1;
                }));
        return dp[sum];
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int[][] nums = new int[n][2];
            IntStream.range(0, n).forEach(i ->
                    Arrays.setAll(nums[i], x -> scanner.nextInt()));
            int sum = scanner.nextInt();
            System.out.println(new SubsetSumLimited(nums, sum).solve() >= 0 ?
                    "Yes" : "No");
        }
    }
}
