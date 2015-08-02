package Chapter3.Section4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
    private void addST(int a, int b, int x, int k, int l, int r) {
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
    private long sumST(int a, int b, int k, int l, int r) {
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

    private long sumBIT(long[] bit, int i) {
        long sum = 0;
        while (i > 0) {
            sum += bit[i];
            i -= i & -i;
        }
        return sum;
    }

    private void addBIT(long[] bit, int i, int n) {
        while (i <= N) {
            bit[i] += n;
            i += i & -i;
        }
    }

    List<Long> solve() {
        long[] bit0 = new long[N + 1];
        long[] bit1 = new long[N + 1];
        List<Long> list = new ArrayList<>();
        for (int i = 1; i <= N; i++)
            addBIT(bit0, i, A[i]);
        for (int i = 0; i < Q; i++) {
            // sum a (1 - i) = sum(bit1, i) + sum(bit0, i)
            final int Li_1 = L[i] - 1;
            if (T[i] == 'C') {
                addBIT(bit0, L[i], -X[i] * (Li_1));
                addBIT(bit1, L[i], X[i]);
                addBIT(bit0, R[i] + 1, X[i] * R[i]);
                addBIT(bit1, R[i] + 1, -X[i]);
            } else {
                long result = 0;
                result += sumBIT(bit0, R[i]) + sumBIT(bit1, R[i]) * R[i];
                result -= sumBIT(bit0, Li_1) + sumBIT(bit1, Li_1) * Li_1;
                list.add(result);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int q = scanner.nextInt();
            int[] a = new int[n];
            Arrays.setAll(a, x -> scanner.nextInt());
            char[] t = new char[q];
            int[] l = new int[q];
            int[] r = new int[q];
            int[] x = new int[q];
            final String[] split = scanner.nextLine().split(" ");
            for (int i = 0; i < q; i++) t[i] = split[i].charAt(0);
            Arrays.setAll(l, i -> scanner.nextInt());
            Arrays.setAll(r, i -> scanner.nextInt());
            Arrays.setAll(x, i -> scanner.nextInt());
            ASimpleProblemWithIntegers query =
                    new ASimpleProblemWithIntegers(a, t, l, r, x);
            query.solve0().forEach(System.out::println);
            query.solve().forEach(System.out::println);
        }
    }
}
