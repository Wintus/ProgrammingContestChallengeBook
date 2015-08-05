package Chapter3.Section5;

/**
 * Master Dynamic Programming of Bit DP.
 * Created by Yuya on 2015/08/04.
 */
public class TravelingSalesmanProblem {
    private final int N;
    private final int distance[][];
    // DP table
    private int[][] dp;

    public TravelingSalesmanProblem(int n, int[][] distance) {
        N = n;
        this.distance = distance;
        dp = new int[1 << N][N];
    }
}
