package Chapter3.Section4;

import java.util.ArrayList;
import java.util.List;

/**
 * Use Segment Tree or Binary Indexed Tree.
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
}
