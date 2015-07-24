package Chapter2.Section6;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Try GCJ Part 1.
 * Created by Yuya on 2015/07/24.
 */
class MinimumScalarProduct {
    private final int N;
    private final int[] vector0, vector1;

    public MinimumScalarProduct(int n, int[] vector0, int[] vector1) {
        N = n;
        this.vector0 = vector0;
        this.vector1 = vector1;
    }

    int solve() {
        Arrays.sort(vector0);
        Arrays.sort(vector1);
        return IntStream.range(0, N).map(i ->
                vector0[i] * vector1[N - 1 - i]).sum();
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int[] v0 = new int[n];
            int[] v1 = new int[n];
            Arrays.setAll(v0, x -> scanner.nextInt());
            Arrays.setAll(v1, x -> scanner.nextInt());
            MinimumScalarProduct msp = new MinimumScalarProduct(n, v0, v1);
            System.out.println(msp.solve());
        }
    }
}
