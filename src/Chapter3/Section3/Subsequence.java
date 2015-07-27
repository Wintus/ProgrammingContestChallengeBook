package Chapter3.Section3;

import java.util.Arrays;
import java.util.Scanner;

/**
 * ÇµÇ·Ç≠Ç∆ÇËñ@ÅB
 * Created by Yuya on 2015/07/26.
 */
public class Subsequence {
    private final int n, S;
    private final int[] a;

    public Subsequence(int s, int[] a) {
        n = a.length;
        S = s;
        this.a = a;
    }

    int solve() {
        int result = n + 1, s = 0, t = 0, sum = 0;
        while (true) {
            while (t < n && sum < S)
                sum += a[t++];
            if (sum < S) break;
            result = Math.min(result, t - s);
            sum -= a[s++];
        }
        return result > n ? 0 : result;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int s = scanner.nextInt();
            int[] a = new int[n];
            Arrays.setAll(a, x -> scanner.nextInt());
            System.out.println(new Subsequence(s, a).solve());
        }
    }
}
