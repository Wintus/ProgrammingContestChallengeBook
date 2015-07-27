package Chapter3.Section2;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * Binary Search. Max Min.
 * Created by Yuya on 2015/07/26.
 */
public class AggressiveCow {
    final private int N, M;
    final private int[] cowshed;

    public AggressiveCow(int m, int[] cowshed) {
        N = cowshed.length;
        M = m;
        this.cowshed = cowshed;
    }

    // greedy algorithm
    // any cows in cowshed are apart more than d
    private boolean condition(int d) {
        int last = 0;
        for (int i = 1; i < M; i++) {
            int j = last + 1;
            while (j < N && cowshed[j] - cowshed[last] < d) ++j;
            if (j == N) return false;
            last = j;
        }
        return true;
    }

    static int lowerBound(Predicate<Integer> predicate) {
        int lb = 0, ub = Integer.MAX_VALUE / 2, mid;
        while (ub - lb > 1) {
            mid = (lb + ub) / 2;
            if (predicate.test(mid))
                lb = mid;
            else
                ub = mid;
        }
        return lb;
    }

    int solve() {
        Arrays.sort(cowshed);
        return lowerBound(this::condition);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int[] c = new int[n];
            Arrays.setAll(c, x -> scanner.nextInt());
            System.out.println(new AggressiveCow(m, c).solve());
        }
    }
}
