package Chapter3.Section5;

import Library.IntMatrix2d;

import java.util.Scanner;

/**
 * Use recurrence relation using matrix.
 * Created by Yuya on 2015/08/11.
 */
public class Blocks {
    public static final IntMatrix2d matrix =
            new IntMatrix2d(new int[][]{{2, 1, 0}, {2, 2, 2}, {0, 1, 2}});

    /**
     * a: both reds and greens are even.
     * b: either reds or greens are odd.
     * c: both reds and greens are odd.
     *
     * @return number of painting pattern both reds and greens are even.
     */
    public static int solve(int n) {
        assert n > 0;
        return matrix.power(n).getAt(0, 0);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            System.out.println(solve(n));
        }
    }
}
