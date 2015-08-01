package Chapter3.Section4;

import java.util.ArrayList;
import java.util.List;

/**
 * Use Segment Tree or Binary Indexed Tree. Deal with Query.
 * Created by Yuya on 2015/08/01.
 */
public class ASimpleProblemWithIntegers {
    private final int N, Q;
    private final int[] A;
    private final char[] T;
    private final int[] L, R, X;
    // data of segment tree
    private long[] data0, data1; // uniform, individual
    // data of BIT
    private long[] bit0, bit1;

    public ASimpleProblemWithIntegers
            (int[] a, char[] t, int[] l, int[] r, int[] x) {
        N = a.length;
        Q = t.length;
        A = a;
        T = t;
        L = l;
        R = r;
        X = x;
    }

    // add x to [a, b). k is node No. to [l, r).
    void addST(int a, int b, int x, int k, int l, int r) {
        if (a <= l && r <= b)
            data0[k] += x;
        else if (l < b && a < r) {
            data1[k] += (Math.min(b, r) - Math.max(a, l)) * x;
            final int mid = (l + r) / 2;
            addST(a, b, x, k * 2 + 1, l, mid);
            addST(a, b, x, k * 2 + 2, mid, r);
        }
    }

    // sum [a, b). node k to [l, r).
    long sumST(int a, int b, int k, int l, int r) {
        if (b <= l || r <= a)
            return 0;
        else if (a <= l && r <= b)
            return data0[k] + (r - 1) * data1[k];
        else {
            long result = (Math.min(b, r) - Math.max(a, l)) * data0[k];
            final int mid = (l + r) / 2;
            result += sumST(a, b, k * 2 + 1, l, mid);
            result += sumST(a, b, k * 2 + 2, mid, r);
            return result;
        }
    }

    List<Long> solve0() {
        int size = (1 << 18) - 1;
        data0 = new long[size];
        data1 = new long[size];
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < N; i++)
            addST(i, i + 1, A[i], 0, 0, N);
        for (int i = 0; i < Q; i++)
            if (T[i] == 'C')
                addST(L[i], R[i] + 1, X[i], 0, 0, N);
            else
                list.add(sumST(L[i], R[i] + 1, 0, 0, N));
        return list;
    }
}
