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
            int left = q[0], right = q[1], k = q[2];
            // lower bound
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
                if (count >= k) ub = mid;
                else lb = mid;
            }
            result.add(ns[ub - 1]);
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
        }
    }
}
