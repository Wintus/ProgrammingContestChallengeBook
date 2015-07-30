package Chapter3.Section3;

import Library.Lookup;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Binary Search using upperBound and lowerBound.
 * Created by Yuya on 2015/07/29.
 */
public class FourValuesWhoseSumIsZero {
    private final int N;
    private final int[] A, B, C, D, AB, CD;

    public FourValuesWhoseSumIsZero(int[] a, int[] b, int[] c, int[] d) {
        A = a;
        B = b;
        C = c;
        D = d;
        N = a.length;
        AB = new int[N * N];
        CD = new int[N * N];
    }

    long solve() {
        Arrays.setAll(AB, i -> A[i / N] + B[i % N]);
        Arrays.setAll(CD, i -> C[i / N] + D[i % N]);
        Arrays.sort(CD);
        return Arrays.stream(AB)
                     .mapToLong(ab -> Lookup.upperBound(CD, -ab)
                             - Lookup.lowerBound(CD, -ab))
                     .sum();
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int[] a = new int[n];
            int[] b = new int[n];
            int[] c = new int[n];
            int[] d = new int[n];
            Arrays.setAll(a, x -> scanner.nextInt());
            Arrays.setAll(b, x -> scanner.nextInt());
            Arrays.setAll(c, x -> scanner.nextInt());
            Arrays.setAll(d, x -> scanner.nextInt());
            FourValuesWhoseSumIsZero four =
                    new FourValuesWhoseSumIsZero(a, b, c, d);
            System.out.println(four.solve());
        }
    }
}
