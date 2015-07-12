package Chapter1;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Problem of Chapter 1.
 * Created by Wintus on 2015/07/11.
 */
class Ants {
    private final int L;
    private final int[] ants;

    public Ants(int l, int[] ants) {
        L = l;
        this.ants = ants;
    }

    void solve() {
        int min, max;
        min = Arrays.stream(ants).map(x -> Math.min(x, L - x)).max().getAsInt();
        max = Arrays.stream(ants).map(x -> Math.max(x, L - x)).max().getAsInt();
        System.out.printf("%d %d\n", min, max);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int l = scanner.nextInt();
            int n = scanner.nextInt();
            int[] ants = new int[n];
            IntStream.range(0, n).forEach(i -> ants[i] = scanner.nextInt());
            new Ants(l, ants).solve();
        }
    }
}
