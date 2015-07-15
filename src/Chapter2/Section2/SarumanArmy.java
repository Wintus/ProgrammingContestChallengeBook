package Chapter2.Section2;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Greedy algorithm.
 * Created by Yuya on 2015/07/12.
 */
public class SarumanArmy {
    private final int R;
    private final int[] points;

    public SarumanArmy(int r, int[] points) {
        R = r;
        this.points = points;
    }

    int solve() {
        Arrays.sort(points);
        int i = 0, n = 0;
        int N = points.length;
        while (i < N) {
            int leftmost = points[i++];
            while (i < N && points[i] <= leftmost + R) ++i;
            int p = points[i - 1];
            while (i < N && points[i] <= p + R) ++i;
            ++n;
        }
        return n;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int r = scanner.nextInt();
            int[] points = IntStream.range(0, n).map(x -> scanner.nextInt())
                              .toArray();
            System.out.println(new SarumanArmy(r, points).solve());
        }
    }
}
