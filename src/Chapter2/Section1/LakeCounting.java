package Chapter2.Section1;

import java.util.Scanner;

/**
 * Using Deep First Search
 * Created by Yuya on 2015/07/11.
 */
class LakeCounting {
    private final Boolean[][] field; // true: lake, false: land
    private final int[][] neighbors =
            {{-1, -1}, {-1, 0}, {-1, 1},
                    {0, -1}, {0, 1},
                    {1, -1}, {1, 0}, {1, 1}};

    public LakeCounting(Boolean[][] field) {
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
                if (field[i][j]) {
                    count++;
                    dfs(i, j);
                }
        return count;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            Boolean[][] field = new Boolean[n][m];
            for (int i = 0; i < n; i++) {
                Boolean[] booleans = scanner.next().chars()
                        .mapToObj(c -> (char) c == 'W')
                        .toArray(Boolean[]::new);
                field[i] = booleans;
            }
            System.out.println(new LakeCounting(field).solve());
        }
    }
}
