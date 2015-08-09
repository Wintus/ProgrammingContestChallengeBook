package Chapter3.Section5;

/**
 * Perfect Matching using Bit DP.
 * Created by Yuya on 2015/08/08.
 */
public class DominoPave {
    private final int N, M, mod;
    private final boolean[][] color; // false: white, true: black
    private final int[][] dp;

    public DominoPave(int mod, boolean[][] color) {
        this.mod = mod;
        this.color = color;
        N = color[0].length;
        M = color[0].length;
        dp = new int[2][1 << M];
    }

    private static boolean isUsedAt(int used, int j) {
        return (used >> j & 1) == 1;
    }

    int solve() {
        int[] current = dp[0], next = dp[1];
        current[0] = 1;

        for (int i = N - 1; i >= 0; i--)
            for (int j = M - 1; j >= 0; j--) {
                for (int used = 0; used < 1 << M; used++)
                    if (isUsedAt(used, j) || color[i][j])
                        // no need to set a block at (i, j)
                        next[used] = current[used & ~(1 << j)];
                        // (i + 1, j) is not yet used and should be blank
                    else {
                        // try two directions
                        int result = 0;
                        // horizontal
                        try {
                            if (!isUsedAt(used, j + 1) && !color[i][j + 1])
                                result += current[used | 1 << (j + 1)];
                        } catch (ArrayIndexOutOfBoundsException ignored) {
                            // pass
                        }
                        // vertical
                        try {
                            if (!color[i + 1][j])
                                result += current[used | 1 << j];
                        } catch (ArrayIndexOutOfBoundsException ignored) {
                            // pass
                        }
                        next[used] = result % mod;
                    }
                current = next;
                next = new int[current.length];
            }
        return current[0];
    }

    public static void main(String[] args) {
        int n = 3, m = 3;
        boolean[][] color = new boolean[n][m];
        color[1][1] = true;
        System.out.println(new DominoPave(1000, color).solve());
    }
}
