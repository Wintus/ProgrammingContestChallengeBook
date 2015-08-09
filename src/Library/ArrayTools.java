package Library;

import java.util.Arrays;

/**
 * Array Tools.
 * Created by Yuya on 2015/08/08.
 */
public class ArrayTools {
    public static void fill2d(int[][] array, int value) {
        for (int[] ints : array) Arrays.fill(ints, value);
    }

    public static void fill2d(double[][] array, double value) {
        for (double[] doubles : array) Arrays.fill(doubles, value);
    }

    public static void fill2d(long[][] array, long value) {
        for (long[] longs : array) Arrays.fill(longs, value);
    }

    public static void fill2d(boolean[][] array, boolean b) {
        for (boolean[] booleans : array) Arrays.fill(booleans, b);
    }
}
