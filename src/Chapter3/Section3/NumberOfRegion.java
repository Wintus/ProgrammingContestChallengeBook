package Chapter3.Section3;

import java.util.*;

/**
 * Coordinate Compression.
 * Created by Yuya on 2015/07/30.
 */
public class NumberOfRegion {
    private final int W, H, N;
    private final int[] x1, x2, y1, y2;
    private boolean[][] filled;

    public NumberOfRegion(int w, int h, int[] x1, int[] x2, int[] y1, int[] y2) {
        W = w;
        H = h;
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        N = x1.length;
        filled = new boolean[N * 3][N * 3];
    }

    private int compress(int[] x1, int[] x2, int w) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            for (int d = -1; d <= 1; d++) {
                int _x1 = x1[i] + d, _x2 = x2[i] + d;
                if (1 <= _x1 && _x1 <= w) set.add(_x1);
                if (2 <= _x2 && _x2 <= w) set.add(_x2);
            }
        }
        List<Integer> xs = new ArrayList<>(set);
        Collections.sort(xs);
        Arrays.setAll(x1, i ->
                xs.contains(x1[i]) ? x1[i] : xs.get(xs.size() - 1)
                        - xs.get(0));
        Arrays.setAll(x2, i ->
                xs.contains(x2[i]) ? x2[i] : xs.get(xs.size() - 1)
                        - xs.get(0));
        return xs.size();
    }
}
