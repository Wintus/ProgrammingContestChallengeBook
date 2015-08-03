package Chapter3.Section4;

import Library.Lookup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
    private static final int B = 1000;

    public KthNumber(int[] a, int[][] query) {
        A = a;
        this.query = query;
        N = a.length;
        ns = new int[N];
    }

    List<Integer> solveB() {
        List<Integer> result = new ArrayList<>(query.length);
        bucket = new ArrayList<>(N / B);
        for (int i = 0; i < N; i++) {
            bucket.get(i / B).add(A[i]);
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
                int c = 0;
                // exceeds of bucket
                while (left < right && left % B != 0) if (A[left++] <= n) ++c;
                while (left < right && right % B != 0) if (A[--right] <= n) ++c;
                // for each bucket
                while (left < right) {
                    final List<Integer> list = bucket.get(left / B);
                    c += Lookup.upperBound(list, n) - list.get(0);
                    left += B;
                }
                if (c >= k) ub = mid;
                else lb = mid;
            }
            result.add(ns[ub]);
        }
        return result;
    }
}
