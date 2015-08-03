package Chapter3.Section4;

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
}
