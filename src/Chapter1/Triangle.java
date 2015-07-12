package Chapter1;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Problem of chapter 1
 * Created by Wintus on 2015/07/10.
 */
class Triangle {
    private final int[] a;

    public Triangle(int[] a) {
        this.a = a;
    }

    void solve() {
        Arrays.sort(a);
        int max = IntStream.range(2, a.length).map(
                i -> a[i] < a[i - 1] + a[i - 2] ? a[i] + a[i - 1] + a[i - 2] : 0
        ).max().getAsInt();
        System.out.println(max);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int[] a = new int[n];
            IntStream.range(0, n).forEach(i -> a[i] = scanner.nextInt());
            new Triangle(a).solve();
        }
    }
}
