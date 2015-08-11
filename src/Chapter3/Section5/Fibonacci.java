package Chapter3.Section5;

import Library.IntMatrix2D;

import java.util.Scanner;

/**
 * Matrix powers.
 * Created by Yuya on 2015/08/09.
 */
public class Fibonacci {
    private static final int[][] a = {{1, 1}, {1, 0}};
    private static final IntMatrix2D A = new IntMatrix2D(a);

    int solve(int n) {
        return A.power(n).getAt(1, 0);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            System.out.println(new Fibonacci().solve(n));
        }
    }
}
