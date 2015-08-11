package Chapter3.Section5;

import Library.IntMatrix2D;

import java.util.Scanner;

/**
 * Use recurrence relation using matrix.
 * Created by Yuya on 2015/08/11.
 */
public class Blocks {
    public static final IntMatrix2D matrix =
            new IntMatrix2D(new int[][]{{2, 1, 0}, {2, 2, 2}, {0, 1, 2}});

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
