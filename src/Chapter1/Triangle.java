package Chapter1;

import java.util.Arrays;
import java.util.Scanner;

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
        int max = 0;
        for (int i = 2; i < a.length; i++) {
            if (a[i] < a[i - 1] + a[i - 2]) {
                max = Math.max(max, a[i] + a[i - 1] + a[i - 2]);
            }
        }
        System.out.println(max);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextInt();
            }
            new Triangle(a).solve();
        }
    }
}
