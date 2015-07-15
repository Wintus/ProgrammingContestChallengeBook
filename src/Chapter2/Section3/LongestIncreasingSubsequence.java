package Chapter2.Section3;

import Library.Lookup;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Dynamic Programming.
 * Created by Yuya on 2015/07/14.
 */
class LongestIncreasingSubsequence {
    private final int[] numbers;
    private final int[] dp;
    private final int N;

    public LongestIncreasingSubsequence(int[] numbers) {
        this.numbers = numbers;
        N = numbers.length;
        dp = new int[N];
    }

    /**
     * dp[i] = length of LIS that its last one is numbers[i].
     * O(n^2).
     *
     * @return length of LIS.
     */
    int solve() {
        return IntStream.range(0, N).map(i -> {
            dp[i] = 1;
            IntStream.range(0, i).forEach(j -> {
                if (numbers[j] < numbers[i])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            });
            return dp[i];
        }).max().getAsInt();
    }

    /**
     * dp[i] = min of the last element of LIS in length i + 1 :? INF.
     * O(n log n).
     *
     * @return length of LIS
     */
    int solve1() {
        Integer[] dp = new Integer[N + 1];
        final int INF = Integer.MAX_VALUE / 2;
        Arrays.fill(dp, INF);
        IntStream.range(0, N).forEach(i ->
                Lookup.lower_bound(dp, numbers[i], x -> numbers[i]));
        return Lookup.lower_bound(dp, INF);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int[] nums = IntStream.range(0, n)
                                  .map(i -> scanner.nextInt()).toArray();
            LongestIncreasingSubsequence lis =
                    new LongestIncreasingSubsequence(nums);
            System.out.println(lis.solve());
            System.out.println(lis.solve1());
        }
    }
}
