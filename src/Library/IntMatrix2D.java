package Library;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * IntMatrix2D.
 * Created by Yuya on 2015/08/09.
 */
public class IntMatrix2D {
    private final int row, column;

    private final int[][] matrix;

    /**
     * Make matrix from int array.
     *
     * @param array int array.
     */
    public IntMatrix2D(int[][] array) {
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
    public IntMatrix2D(int row, int column) {
        this.row = row;
        this.column = column;
        matrix = new int[row][column];
    }

    /**
     * Make size-by-size int matrix.
     *
     * @param size size of matrix.
     */
    public IntMatrix2D(int size) {
        this(size, size);
    }

    /**
     * Default 2-by-2 int matrix.
     */
    public IntMatrix2D() {
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

    public static IntMatrix2D operate(IntMatrix2D A, IntMatrix2D B,
                                      BiFunction<Integer, Integer, Integer> operator) {
        IntMatrix2D C = new IntMatrix2D(A.row, B.column);
        for (int i = 0; i < A.row; i++)
            for (int k = 0; k < B.row; k++)
                for (int j = 0; j < B.column; j++)
                    C.matrix[i][j] += operator.apply(A.getAt(i, k), B.getAt(k, j));
        return C;
    }

    static IntMatrix2D operate1(IntMatrix2D A, IntMatrix2D B,
                                       BiFunction<Integer, Integer, Integer> operator1,
                                       Function<Integer, Integer> operator2) {
        IntMatrix2D C = new IntMatrix2D(A.row, B.column);
        for (int i = 0; i < A.row; i++)
            for (int k = 0; k < B.row; k++)
                for (int j = 0; j < B.column; j++) {
                    C.matrix[i][j] += operator1.apply(A.getAt(i, k), B.getAt(k, j));
                    C.matrix[i][j] = operator2.apply(C.getAt(i, j));
                }
        return C;
    }

    static IntMatrix2D operate2(IntMatrix2D A, IntMatrix2D B,
                                       BiFunction<Integer, Integer, Integer> operator1,
                                       int n,
                                       BiFunction<Integer, Integer, Integer> operator2) {
        IntMatrix2D C = new IntMatrix2D(A.row, B.column);
        for (int i = 0; i < A.row; i++)
            for (int k = 0; k < B.row; k++)
                for (int j = 0; j < B.column; j++) {
                    C.matrix[i][j] += operator1.apply(A.getAt(i, k), B.getAt(k, j));
                    C.matrix[i][j] = operator2.apply(C.getAt(i, j), n);
                }
        return C;
    }

    public static IntMatrix2D add(IntMatrix2D A, IntMatrix2D B) {
        assert A.row == B.row && A.column == B.column;
        return operate(A, B, (a, b) -> a + b);
    }

    public IntMatrix2D add(IntMatrix2D B) {
        return add(this, B);
    }

    public static IntMatrix2D addMod(IntMatrix2D A, IntMatrix2D B, int mod) {
        assert A.row == B.row && A.column == B.column;
        return operate1(A, B, (a, b) -> a + b, n -> n % mod);
    }

    public IntMatrix2D addMod(IntMatrix2D B, int mod) {
        return addMod(this, B, mod);
    }

    public static IntMatrix2D multiply(IntMatrix2D A, IntMatrix2D B) {
        assert A.column == B.row;
        return operate(A, B, (a, b) -> a * b);
    }

    public IntMatrix2D multiply(IntMatrix2D B) {
        return multiply(this, B);
    }

    public static IntMatrix2D multiplyMod(IntMatrix2D A, IntMatrix2D B, int mod) {
        assert A.column == B.row;
        return operate1(A, B, (a, b) -> a * b, n -> n % mod);
    }

    public IntMatrix2D multiplyMod(IntMatrix2D B, int mod) {
        return multiplyMod(this, B, mod);
    }

    public static IntMatrix2D power(IntMatrix2D A, int n) {
        assert A.isSquare();
        IntMatrix2D B = new IntMatrix2D(A.row, A.column);
        int min = Math.min(A.row, A.column);
        for (int i = 0; i < min; i++) B.matrix[i][i] = 1;
        while (n > 0) {
            if ((n & 1) == 1) B = multiply(A, B);
            A = multiply(A, A);
            n >>= 1;
        }
        return B;
    }

    public IntMatrix2D power(int n) {
        return power(this, n);
    }

    public static IntMatrix2D powerMod(IntMatrix2D A, int n, int mod) {
        assert A.isSquare();
        IntMatrix2D B = new IntMatrix2D(A.row, A.column);
        int min = Math.min(A.row, A.column);
        for (int i = 0; i < min; i++) B.matrix[i][i] = 1;
        while (n > 0) {
            if ((n & 1) == 1) B = multiplyMod(A, B, mod);
            A = multiplyMod(A, A, mod);
            n >>= 1;
        }
        return B;
    }

    public IntMatrix2D powerMod(int n, int mod) {
        return powerMod(this, n, mod);
    }
}
