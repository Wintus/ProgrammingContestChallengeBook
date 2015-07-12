package Chapter1;

import java.util.Arrays;
import java.util.Scanner;

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

    void solve() {
        int n = lots.length;
        int[] lots2 = new int[n * (n + 1) / 2];
        for (int c = 0; c < n; c++)
            for (int d = c; d < n; d++)
                lots2[c * n + d - c * (c + 1) / 2] = lots[c] + lots[d];
        Arrays.sort(lots2);
        boolean found = false;
        for (int a = 0; a < n; a++)
            for (int b = a; b < n; b++)
                if (Arrays.binarySearch(lots2, sum - lots[a] - lots[b]) >= 0) {
                    found = true;
                    break;
                }
        System.out.println(found ? "Yes" : "No");
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int sum = scanner.nextInt();
            int[] lots = new int[n];
            for (int i = 0; i < n; i++) lots[i] = scanner.nextInt();
            new Lottery(sum, lots).solve();
        }
    }
}
