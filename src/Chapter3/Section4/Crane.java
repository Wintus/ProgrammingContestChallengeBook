package Chapter3.Section4;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Segment Tree.
 * Created by Yuya on 2015/07/30.
 */
public class Crane {
    private final int N, C;
    private final int[] L, S, A;
    // segment tree data
    private final double[] vx, vy, angle, prev_ang;

    public Crane(int[] l, int[] s, int[] a) {
        L = l;
        S = s;
        A = a;
        N = L.length;
        C = S.length;
        int size = (1 << 15) - 1;
        vx = new double[size];
        vy = new double[size];
        angle = new double[size];
        prev_ang = new double[N];
    }

    private void init(int node, int left, int right) {
        angle[node] = vx[node] = 0.0;
        if (right - left == 1) // isLeaf
            vy[node] = L[left];
        else { // isNode
            int childLeft = node * 2 + 1, childRight = node * 2 + 2;
            final int middle = (left + right) / 2;
            init(childLeft, left, middle);
            init(childRight, middle, right);
            vy[node] = vy[childLeft] + vy[childRight];
        }
    }

    // s.a += dAng
    // node is in [left, right)
    private void change(int line, double dAng, int node, int left, int right) {
        if (1 <= line && right - left > 1) { //isValidLine & isNode
            int childL = node * 2 + 1, childR = node * 2 + 2;
            int middle = (left + right) / 2;
            change(line, dAng, childL, left, middle);
            change(line, dAng, childR, middle, right);
            if (line - 1 < middle) angle[node] += dAng;
            double sin = Math.sin(angle[node]), cos = Math.cos(angle[node]);
            vx[node] = vx[childL] + (cos * vx[childR] - sin * vy[childR]);
            vy[node] = vy[childL] + (sin * vx[childR] + cos * vy[childR]);
        }
    }

    double[][] solve() {
        double[][] result = new double[C][2];
        init(0, 0, N);
        Arrays.fill(prev_ang, Math.PI);
        for (int i = 0; i < C; i++) {
            int s = S[i];
            double ang = A[i] / 360.0 * 2 * Math.PI; // rad
            change(s, ang - prev_ang[s], 0, 0, N);
            prev_ang[i] = ang;
            result[i] = new double[]{vx[0], vy[0]};
        }
        return result;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int c = scanner.nextInt();
            int[] line = new int[n];
            int[] s = new int[c];
            int[] ang = new int[c];
            Arrays.setAll(line, x -> scanner.nextInt());
            Arrays.setAll(s, x -> scanner.nextInt());
            Arrays.setAll(ang, x -> scanner.nextInt());
            for (double[] end : new Crane(line, s, ang).solve())
                System.out.printf("%.2f %.2f\n", end[0], end[1]);
        }
    }
}
