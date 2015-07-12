package Chapter2;

/**
 * Using Deep First Search
 * Created by Yuya on 2015/07/11.
 */
public class LakeCounting {
    boolean[][] field; // true: lake, false: land
    final private int[][] neighbors =
            {{-1, -1}, {-1, 0}, {-1, 1},
                    {0, -1}, {0, 1},
                    {1, -1}, {1, 0}, {1, 1}};

    public LakeCounting(boolean[][] field) {
        this.field = field;
    }

    private void dfs(int x, int y) {
        field[x][y] = false;
        for (int[] neighbor : neighbors) {
            int nx = x + neighbor[0], ny = y + neighbor[1];
            try {
                if (field[nx][ny]) dfs(nx, ny);
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
        }
    }

    int solve() {
        int count = 0;
        for (int i = 0; i < field.length; i++)
            for (int j = 0; j < field[0].length; j++)
                if (field[i][j]) dfs(i, j);
        return count;
    }
}
