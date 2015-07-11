package Chapter1;

import java.util.Arrays;

/**
 * Problem of Chapter 1.
 * Created by Wintus on 2015/07/11.
 */
public class Lottery {
    int sum;
    int[] lots;

    public Lottery(int sum, int[] lots) {
        this.sum = sum;
        this.lots = lots;
    }

    void solve() {
        int n = lots.length;
        int[] lots2 = new int[n * (n + 1) / 2];
        for (int c = 0; c < n; c++) {
            for (int d = c; d < n; d++) {
                lots2[c * n + n - d] = lots[c] + lots[d];
            }
        }
        Arrays.sort(lots2);
        for (int a = 0; a < n; a++) {
            for (int b = a; b < n; b++) {
                if (Arrays.binarySearch(lots2, sum - lots[a] - lots[b]) >= 0) {
                    System.out.println("Yes");
                    return;
                }
            }
        }
        System.out.println("No");
    }
}
