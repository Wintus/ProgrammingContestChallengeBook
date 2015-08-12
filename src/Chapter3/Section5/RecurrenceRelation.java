package Chapter3.Section5;

import Library.IntMatrix2d;

/**
 * Recurrence relation using matrix.
 * Created by Yuya on 2015/08/11.
 */
public class RecurrenceRelation {
    private final int order;
    private final IntMatrix2d matrix, vector;

    public RecurrenceRelation(int order, int[] coefficients, int[] initials) {
        assert order > 0 && order == coefficients.length && order == initials.length;
        this.order = order;
        matrix = new IntMatrix2d(order);
        System.arraycopy(coefficients, 0, matrix.getMatrix()[0], 0, order);
        for (int i = 0; i < order - 1; i++) matrix.getMatrix()[i + 1][i] = 1;
        vector = new IntMatrix2d(order, 1);
        for (int i = 0; i < order; i++) vector.getMatrix()[i][0] = initials[i];
    }

    public RecurrenceRelation(int order, int[] coefficients, int constant, int[] initials) {
        this(order, coefficients, initials);
        matrix.setAt(order, order, constant);
        vector.setAt(order, 0, 1);
    }

    public int find(int n) {
        return matrix.power(n).multiply(vector).getAt(order - 1, 0);
    }
}
