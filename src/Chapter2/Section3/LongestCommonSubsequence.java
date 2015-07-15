package Chapter2.Section3;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Dynamic Programming.
 * Created by Yuya on 2015/07/14.
 */
class LongestCommonSubsequence {
    private final String string0;
    private final String string1;
    private final int[][] dp;

    public LongestCommonSubsequence(String string0, String string1) {
        this.string0 = string0;
        this.string1 = string1;
        int n = string0.length() + 1;
        dp = new int[n][string1.length() + 1];
        IntStream.range(0, n).forEach(i ->
                Arrays.setAll(dp[i], x -> 0));
    }

    int solve() {
        int n = string0.length(), m = string1.length();
        // @formatter:off
        IntStream.range(0, n).forEach(i ->
            IntStream.range(0, m).forEach(j ->
                dp[i + 1][j + 1] = string0.charAt(i) == string1.charAt(j) ?
                    dp[i][j] + 1 : Math.max(dp[i][j + 1], dp[i + 1][j])));
        return dp[n][m];
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String str0 = scanner.next();
            String str1 = scanner.next();
            System.out.println(
                    new LongestCommonSubsequence(str0, str1).solve());
        }
    }
}
