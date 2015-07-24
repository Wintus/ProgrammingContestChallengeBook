package Chapter2.Section6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * Try GCJ Part 1.
 * Created by Yuya on 2015/07/24.
 */
public class CrazyRows {
    private int N;
    private int[][] matrix;
    private ArrayList<Integer> last1;

    public CrazyRows(int n, int[][] matrix) {
        N = n;
        this.matrix = matrix;
        last1 = new ArrayList<>(n);
    }

    int solve() {
        int result = 0;
        for (int i = 0; i < N; i++) {
            last1.add(-1);
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] == 1) last1.set(i, j);
            }
        }
        for (int i = 0; i < N; i++) {
            // find the row to be moved to i
            int position = -1;
            for (int j = i; j < N; j++) {
                if (last1.get(j) <= i) {
                    position = j;
                    break;
                }
            }
            // move it by swap
            for (int j = position; j > i; j--) {
                Collections.swap(last1, j, j - 1);
                ++result;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int[][] m = new int[n][n];
            for (int[] row : m) Arrays.setAll(row, x -> scanner.nextInt());
            CrazyRows crazyRows = new CrazyRows(n, m);
            System.out.println(crazyRows.solve());
        }
    }
}
