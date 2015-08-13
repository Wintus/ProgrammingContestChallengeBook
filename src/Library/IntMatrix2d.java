package Library;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * IntMatrix2D.
 * Created by Yuya on 2015/08/09.
 */
public class IntMatrix2d {
    public final int row, column;
    private final int[][] matrix;

    private interface IntOperator extends Function<Integer, Integer> {
    }

    private interface BiIntOperator extends BiFunction<Integer, Integer, Integer> {
    }

    /**
     * Make matrix from int array.
     *
     * @param array int array.
     */
    public IntMatrix2d(int[][] array) {
        matrix = array;
        row = array.length;
        column = array[0].length;
    }

    /**
     * Make row-by-column int matrix.
     *
     * @param row    number of rows.
     * @param column number of columns.
     */
    public IntMatrix2d(int row, int column) {
        this.row = row;
        this.column = column;
        matrix = new int[row][column];
    }

    /**
     * Make size-by-size int matrix.
     *
     * @param size size of matrix.
     */
    public IntMatrix2d(int size) {
        this(size, size);
    }

    /**
     * Default 2-by-2 int matrix.
     */
    public IntMatrix2d() {
        this(2);
    }

    public int getAt(int i, int j) {
        return matrix[i][j];
    }

    public void setAt(int i, int j, int n) {
        matrix[i][j] = n;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public boolean isSquare() {
        return row == column;
    }

    public static IntMatrix2d identity(int size) {
        final IntMatrix2d I = new IntMatrix2d(size);
        for (int i = 0; i < size; i++) I.setAt(i, i, 1);
        return I;
    }

    public static IntMatrix2d identity() {
        return identity(2);
    }

    static IntMatrix2d operate(IntMatrix2d A, IntMatrix2d B, BiIntOperator operator) {
        IntMatrix2d C = new IntMatrix2d(A.row, B.column);
        for (int i = 0; i < A.row; i++)
            for (int k = 0; k < B.row; k++)
                for (int j = 0; j < B.column; j++)
                    C.matrix[i][j] += operator.apply(A.getAt(i, k), B.getAt(k, j));
        return C;
    }

    static IntMatrix2d operate1(IntMatrix2d A, IntMatrix2d B, BiIntOperator operator1,
                                IntOperator operator2) {
        IntMatrix2d C = new IntMatrix2d(A.row, B.column);
        for (int i = 0; i < A.row; i++)
            for (int k = 0; k < B.row; k++)
                for (int j = 0; j < B.column; j++) {
                    C.matrix[i][j] += operator1.apply(A.getAt(i, k), B.getAt(k, j));
                    C.matrix[i][j] = operator2.apply(C.getAt(i, j));
                }
        return C;
    }

    static IntMatrix2d operate2(IntMatrix2d A, IntMatrix2d B, BiIntOperator operator1,
                                int n, BiIntOperator operator2) {
        IntMatrix2d C = new IntMatrix2d(A.row, B.column);
        for (int i = 0; i < A.row; i++)
            for (int k = 0; k < B.row; k++)
                for (int j = 0; j < B.column; j++) {
                    C.matrix[i][j] += operator1.apply(A.getAt(i, k), B.getAt(k, j));
                    C.matrix[i][j] = operator2.apply(C.getAt(i, j), n);
                }
        return C;
    }

    public static IntMatrix2d add(IntMatrix2d A, IntMatrix2d B) {
        assert A.row == B.row && A.column == B.column;
        return operate(A, B, (a, b) -> a + b);
    }

    public IntMatrix2d add(IntMatrix2d B) {
        return add(this, B);
    }

    public static IntMatrix2d addMod(IntMatrix2d A, IntMatrix2d B, int mod) {
        assert A.row == B.row && A.column == B.column;
        return operate1(A, B, (a, b) -> a + b, n -> n % mod);
    }

    public IntMatrix2d addMod(IntMatrix2d B, int mod) {
        return addMod(this, B, mod);
    }

    public static IntMatrix2d multiply(IntMatrix2d A, IntMatrix2d B) {
        assert A.column == B.row;
        return operate(A, B, (a, b) -> a * b);
    }

    public IntMatrix2d multiply(IntMatrix2d B) {
        return multiply(this, B);
    }

    public static IntMatrix2d multiplyMod(IntMatrix2d A, IntMatrix2d B, int mod) {
        assert A.column == B.row;
        return operate1(A, B, (a, b) -> a * b, n -> n % mod);
    }

    public IntMatrix2d multiplyMod(IntMatrix2d B, int mod) {
        return multiplyMod(this, B, mod);
    }

    public static IntMatrix2d power(IntMatrix2d A, int n) {
        assert A.isSquare();
        IntMatrix2d B = identity(A.row);
        while (n > 0) {
            if ((n & 1) == 1) B = multiply(A, B);
            A = multiply(A, A);
            n >>= 1;
        }
        return B;
    }

    public IntMatrix2d power(int n) {
        return power(this, n);
    }

    public static IntMatrix2d powerMod(IntMatrix2d A, int n, int mod) {
        assert A.isSquare();
        IntMatrix2d B = identity(A.row);
        while (n > 0) {
            if ((n & 1) == 1) B = multiplyMod(A, B, mod);
            A = multiplyMod(A, A, mod);
            n >>= 1;
        }
        return B;
    }

    public IntMatrix2d powerMod(int n, int mod) {
        return powerMod(this, n, mod);
    }
}
