package Chapter2.Section6;

import java.util.ArrayList;

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
}
