package Library;

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
        this(new int[row][column]);
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

    public static IntMatrix2D multiply(IntMatrix2D A, IntMatrix2D B) {
        IntMatrix2D C = new IntMatrix2D(A.row, B.column);
        for (int i = 0; i < A.row; i++)
            for (int k = 0; k < B.row; k++)
                for (int j = 0; j < B.column; j++)
                    C.matrix[i][j] += A.getAt(i, k) * B.getAt(k, j);
        return C;
    }

    public IntMatrix2D multiply(IntMatrix2D B) {
        return multiply(this, B);
    }

    public static IntMatrix2D power(IntMatrix2D A, int n) {
        IntMatrix2D B = new IntMatrix2D(A.row, A.column);
        try {
            for (int i = 0; i < A.row; i++) B.matrix[i][i] = 1;
        } catch (ArrayIndexOutOfBoundsException ignored) {
            // pass
        }
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
}
