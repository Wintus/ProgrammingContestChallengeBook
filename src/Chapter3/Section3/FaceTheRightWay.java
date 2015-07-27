package Chapter3.Section3;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Divide in segments.
 * Created by Yuya on 2015/07/27.
 */
public class FaceTheRightWay {
    private final int N;
    private final int[] dir; // 0:F, 1:B
    private int[] flipped;

    public FaceTheRightWay(int[] dir) {
        this.dir = dir;
        N = dir.length;
        flipped = new int[N];
    }

    // return min M for K
    // flipped[i] = is flipped range [i, i + K - 1] ? 1 : 0
    private int calc(int K) {
        final int K_1 = K - 1;
        Arrays.fill(flipped, 0);
        int result = 0, count = 0; // count of flip
        for (int i = 0; i + K_1 < N; i++) {
            // see [i, i + K - 1]
            if ((dir[i] + count) % 2 != 0) {
                // first cow heads back
                ++result;
                flipped[i] = 1;
            }
            count += flipped[i];
            if (i - K_1 >= 0)
                count -= flipped[i - K_1];
        }
        // check whether the rest of cows heading front
        for (int i = N - K_1; i < N; i++) {
            if ((dir[i] + count) % 2 != 0)
                return -1;
            if (i - K_1 >= 0)
                count -= flipped[i - K_1];
        }
        return result;
    }

    // return {K, M}
    int[] solve() {
        int K = 1, M = N;
        for (int k = 1; k < N; k++) {
            int m = calc(k);
            if (0 <= m && m < M) {
                M = m;
                K = k;
            }
        }
        return new int[]{K, M};
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
//            int n = scanner.nextInt();
            String s = scanner.next();
            int[] cows = new int[s.length()];
            Arrays.setAll(cows, i -> s.charAt(i) == 'F' ? 0 : 1);
            int[] K_M = new FaceTheRightWay(cows).solve();
            System.out.printf("%d %d\n", K_M[0], K_M[1]);
        }
    }
}
