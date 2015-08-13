package Library;

import java.util.Arrays;
import java.util.Scanner;

/**
 * RMQ implemented by Segment Tree.
 * Created by Yuya on 2015/08/12.
 */
public class RangeMinimumQuery {
    private final int N;
    private final int[] data;
    public static final int maxInt = Integer.MAX_VALUE / 2;

    public static int highestBit(int n) {
        int _n;
        do {
            _n = n;
            n |= n >> 1;
        } while (_n != n);
        return n - (n >>> 1);
    }

    public RangeMinimumQuery(int n) {
        assert n > 0;
        N = Integer.highestOneBit(n) << 1;
        data = new int[2 * N - 1];
        initialize();
    }

    public RangeMinimumQuery(int[] ns) {
        this(ns.length);
        for (int i = 0, length = ns.length; i < length; i++) update(i, ns[i]);
    }

    public void initialize() {
        Arrays.fill(data, maxInt);
    }

    /**
     * set the value at index to n.
     *
     * @param index index.
     * @param n     value to be set.
     */
    public void update(int index, int n) {
        assert 0 <= index && index < N;
        int node = index + N - 1;
        data[node] = n;
        while (node > 0) {
            node = (node - 1) / 2;
            data[node] = Math.min(data[node * 2 + 1], data[node * 2 + 2]);
        }
    }

    private int _query(int a, int b, int node, int left, int right) {
        // no intersection between [a, b) and [l, r)
        if (right <= a || b <= left) return maxInt;
        // [a, b) contains [l, r)
        if (a <= left && right <= b) return data[node];
            // minimum of children
        else {
            int middle = (left + right) / 2;
            int childL = _query(a, b, node * 2 + 1, left, middle);
            int childR = _query(a, b, node * 2 + 2, middle, right);
            return Math.min(childL, childR);
        }
    }

    /**
     * find the minimum in [a, b)
     *
     * @param a the lower bound of the range.
     * @param b the upper bound of the range.
     * @return minimum value within the range.
     */
    public int query(int a, int b) {
        assert a < b;
        return _query(a, b, 0, 0, N);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int[] ns = new int[]{5, 3, 7, 9, 6, 4, 1, 2};
            RangeMinimumQuery RMQ = new RangeMinimumQuery(ns);
            int s = scanner.nextInt(), t = scanner.nextInt();
            while (s < t) {
                System.out.println(RMQ.query(s, t));
                s = scanner.nextInt();
                t = scanner.nextInt();
            }
        }
    }
}
