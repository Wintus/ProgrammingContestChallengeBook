package Chapter3.Section4;

/**
 * Segment Tree.
 * Created by Yuya on 2015/07/30.
 */
public class Crane {
    private int[] L, S, A;
    // segment tree data
    private double[] vx, vy, ang, prev;

    public Crane(int[] l, int[] s, int[] a) {
        L = l;
        S = s;
        A = a;
        int size = (1 << 15) - 1;
        vx = new double[size];
        vy = new double[size];
        ang = new double[size];
        prev = new double[L.length];
    }
}
