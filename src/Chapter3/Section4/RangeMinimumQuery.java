package Chapter3.Section4;

import java.util.Arrays;

/**
 * Data structure. RMQ by Segment Tree.
 * Created by Yuya on 2015/07/30.
 */
public class RangeMinimumQuery {
    private int N;
    private int[] data;
    public static final int INF = Integer.MAX_VALUE / 2;

    public RangeMinimumQuery(int n) {
        N = 1;
        while (N < n) N *= 2;
        data = new int[2 * N - 1];
        Arrays.fill(data, INF);
    }

    void update(int k, int a) {
        k += N - 1; // node
        data[k] = a;
        while (k > 0) {
            k = (k - 1) / 2;
            data[k] = Math.min(data[k * 2 + 1], data[k * 2 + 2]);
        }
    }

    /**
     * find the min in [a, b)
     */
    private int query(int a, int b, int k, int l, int r) {
        // no intersection of [a, b) and [l, r)
        if (r <= a || b <= l) return INF;
        // [a, b) involves [l, r)
        else if (a <= l && r <= b) return data[k];
        else {
            int valueL = query(a, b, k * 2 + 1, l, (l + r) / 2);
            int valueR = query(a, b, k * 2 + 2, (l + r) / 2, r);
            return Math.min(valueL, valueR);
        }
    }

    int query(int a, int b) {
        return query(a, b, 0, 0, N);
    }
}
