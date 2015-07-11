package Chapter1;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Problem of Chapter 1.
 * Created by Wintus on 2015/07/11.
 */
public class Ants {
    private int L;
    private int[] ants;

    public Ants(int l, int[] ants) {
        L = l;
        this.ants = ants;
    }

    void solve() {
        int min;
        int max;
        min = Arrays.stream(ants).map(x -> Math.min(x, L - x)).max().getAsInt();
        max = Arrays.stream(ants).map(x -> Math.max(x, L - x)).max().getAsInt();
        System.out.printf("%d %d\n", min, max);
    }
}
