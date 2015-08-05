package Chapter3.Section4;

import Library.Lookup;

import java.util.*;

/**
 * Bucket Method of Sqrt Decomposition.
 * Created by Yuya on 2015/08/02.
 */
public class KthNumber {
    private final int N;
    private final int[] A;
    private final int[][] query; // {{i, j, k}}
    private final int[] ns; // sorted A
    // bucket
    List<List<Integer>> bucket; // sorted buckets
    // segment tree
    List<List<Integer>> data; // sorted buckets

    public KthNumber(int[] a, int[][] query) {
        A = a;
        this.query = query;
        N = a.length;
        ns = new int[N];
    }

    List<Integer> solveB() {
        final int B = (int) Math.sqrt(N);
        List<Integer> result = new ArrayList<>(query.length);
        bucket = new ArrayList<>(N / B);
        for (int i = 0; i < N; i++) {
            try {
                bucket.get(i / B).add(A[i]);
            } catch (IndexOutOfBoundsException e) {
                bucket.add(new ArrayList<>());
                bucket.get(i / B).add(A[i]);
            }
            ns[i] = A[i];
        }
        Arrays.sort(ns);
        bucket.forEach(Collections::sort);
        // deal query
        for (int[] q : query) {
            int left = q[0] - 1, right = q[1], k = q[2] - 1;
            // upperBound
            int lb = -1, ub = N - 1;
            while (ub - lb > 1) {
                int mid = (lb + ub) / 2;
                int n = ns[mid];
                int _left = left, _right = right, count = 0;
                // exceeds of bucket
                while (_left < _right && _left % B != 0)
                    if (A[_left++] <= n) ++count;
                while (_left < _right && _right % B != 0)
                    if (A[--_right] <= n) ++count;
                // for each bucket
                while (_left < _right) {
                    int b = _left / B;
                    count += Lookup.upperBound(bucket.get(b), n);
                    _left += B;
                }
                if (count > k) ub = mid;
                else lb = mid;
            }
            result.add(ns[ub]);
        }
        return result;
    }

    // node k in [left, right)
    void init(int k, int left, int right) {
        if (right - left == 1) {
            while (data.size() <= k) data.add(new ArrayList<>());
            data.set(k, new ArrayList<>(1));
            data.get(k).add(A[left]);
        } else {
            int childLeft = k * 2 + 1, childRight = k * 2 + 2;
            int middle = (left + right) / 2;
            init(childLeft, left, middle);
            init(childRight, middle, right);
            data.set(k, new ArrayList<>(right - left));
            // merge two children
            data.get(k).addAll(data.get(childLeft));
            data.get(k).addAll(data.get(childRight));
            data.get(k).sort(Integer::compare);
        }
    }

    // deal query: count the numbers less than n in [a, b)
    // node k in [left, right)
    int query(int a, int b, int n, int k, int left, int right) {
        if (right <= a || b <= left) // never intersects
            return 0;
        else if (a <= left && right <= b) // [a, b) contains [left, right)
            return Lookup.upperBound(data.get(k), n);
        else {
            // find recursively for children
            int middle = (left + right) / 2;
            int childLeft = query(a, b, n, k * 2 + 1, left, middle);
            int childRight = query(a, b, n, k * 2 + 2, middle, right);
            return childLeft + childRight;
        }
    }

    List<Integer> solveST() {
        List<Integer> result = new ArrayList<>(query.length);
        final int capacity = 2 * N;
        data = new ArrayList<>(capacity);
        System.arraycopy(A, 0, ns, 0, N);
        Arrays.sort(ns);
        init(0, 0, N);

        for (int[] q : query) {
            int left = q[0] - 1, right = q[1], k = q[2] - 1;
            int lb = -1, ub = N - 1;
            // upperBound
            while (ub - lb > 1) {
                int mid = (lb + ub) / 2;
                int c = query(left, right, ns[mid], 0, 0, N);
                if (c > k) ub = mid;
                else lb = mid;
            }
            result.add(ns[ub]);
        }
        return result;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int[] a = new int[n];
            Arrays.setAll(a, x -> scanner.nextInt());
            int[][] qs = new int[m][3];
            for (int[] q : qs) Arrays.setAll(q, x -> scanner.nextInt());
            KthNumber kthNumber = new KthNumber(a, qs);
            kthNumber.solveB().forEach(System.out::println);
            System.out.println();
            kthNumber.solveST().forEach(System.out::println);
        }
    }
}
