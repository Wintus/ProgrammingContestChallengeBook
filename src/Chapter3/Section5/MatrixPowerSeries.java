package Chapter3.Section5;

import Library.IntMatrix2d;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Matrix.
 * Created by Yuya on 2015/08/12.
 */
public class MatrixPowerSeries {
    private final IntMatrix2d A, B;

    public MatrixPowerSeries(int[][] matrix) {
        A = new IntMatrix2d(matrix);
        B = new IntMatrix2d(A.row * 2, A.column * 2);
        for (int i = 0; i < A.row; i++) {
            for (int j = 0; j < A.column; j++)
                B.setAt(i, j, A.getAt(i, j));
            B.setAt(A.column + i, i, 1);
            B.setAt(A.column + i, A.row + i, 1);
        }
    }

    int[][] solve(int power, int mod) {
        final IntMatrix2d b = B.powerMod(power + 1, mod);
        final int[][] matrix = new int[A.row][A.column];
        for (int i = 0; i < A.row; i++)
            for (int j = 0; j < A.column; j++)
                matrix[i][j] = b.getAt(i, j);
        return matrix;
    }

    int[][] solve0(int power, int mod) {
        final int[][] b = B.powerMod(power + 1, mod).getMatrix();
        final int[][] matrix = new int[A.row][A.column];
        for (int i = 0; i < A.row; i++)
            System.arraycopy(b[i], 0, matrix[i], 0, A.column);
        return matrix;
    }

    int[][] solve1(int power, int mod) {
        final IntMatrix2d I0 = new IntMatrix2d(A.row * 2, A.column);
        for (int i = 0; i < A.column; i++) I0.setAt(i, i, 1);
        final int[][] b = B.powerMod(power + 1, mod).multiply(I0).getMatrix();
        final int[][] matrix = new int[A.row][A.column];
        System.arraycopy(b, 0, matrix, 0, A.row);
        return matrix;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            int m = scanner.nextInt();
            int[][] mat = new int[n][n];
            for (int[] row : mat) Arrays.setAll(row, col -> scanner.nextInt());
            final int[][] sumMat = new MatrixPowerSeries(mat).solve(k, m);
            final String s = Arrays.stream(sumMat)
                                   .map(Arrays::toString)
                                   .collect(Collectors.joining(",\n ", "[", "]"));
            System.out.println(s);
        }
    }
}
