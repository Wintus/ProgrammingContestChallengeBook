package Chapter2.Section1;

import java.util.Scanner;
import java.util.stream.IntStream;

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
        return IntStream.range(0, field.length).flatMap(i ->
                IntStream.range(0, field[0].length).map(j -> {
                            if (field[i][j]) {
                                dfs(i, j);
                                return 1;
                            } else return 0;
                        }
                )).sum();
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            Boolean[][] field = new Boolean[n][m];
            IntStream.range(0, n).forEach(
                    i -> field[i] = scanner.next().substring(0, m).chars()
                            .mapToObj(c -> (char) c == 'W')
                            .toArray(Boolean[]::new)
            );
            System.out.println(new LakeCounting(field).solve());
        }
    }
}
