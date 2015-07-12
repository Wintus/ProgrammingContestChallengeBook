package Chapter1;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Problem of Chapter 1.
 * Created by Wintus on 2015/07/11.
 */
class Lottery {
    private final int sum;
    private final int[] lots;

    public Lottery(int sum, int[] lots) {
        this.sum = sum;
        this.lots = lots;
    }

    // @formatter:off
   void solve() {
        int n = lots.length;
        int[] lots2 = new int[n * (n + 1) / 2];
        IntStream.range(0, n).parallel().forEach(c ->
           IntStream.range(c, n).parallel().forEach(d ->
                lots2[c * n + d - c * (c + 1) / 2] = lots[c] + lots[d]));
        Arrays.sort(lots2);
        OptionalInt first =
            IntStream.range(0, n).parallel().flatMap(a ->
                IntStream.range(a, n).parallel().map(b ->
                    Arrays.binarySearch(lots2, sum - lots[a] - lots[b])
                )).filter(i -> i >= 0).findFirst();
        System.out.println(first.isPresent() ? "Yes" : "No");
    }

    // @formatter:on
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int sum = scanner.nextInt();
            int[] lots = new int[n];
            IntStream.range(0, n).forEach(i -> lots[i] = scanner.nextInt());
            new Lottery(sum, lots).solve();
        }
    }
}
