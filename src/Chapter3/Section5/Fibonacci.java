package Chapter3.Section5;

import Library.IntMatrix2d;

import java.util.Scanner;

/**
 * Matrix powers.
 * Created by Yuya on 2015/08/09.
 */
public class Fibonacci {
    private static final IntMatrix2d A = new IntMatrix2d(new int[][]{{1, 1}, {1, 0}});
    private static final RecurrenceRelation fib =
            new RecurrenceRelation(2, new int[]{1, 1}, new int[]{1, 0});

    public static int solve(int n) {
        return A.power(n).getAt(1, 0);
    }

    public static int solve1(int n) {
        return fib.find(n);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            System.out.println(solve(n));
            System.out.println(solve1(n));
        }
    }
}
