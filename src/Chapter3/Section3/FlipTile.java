package Chapter3.Section3;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Use integer literal of set.
 * Created by Yuya on 2015/07/27.
 */
class FlipTile {
    private final int M, N;
    private final int[][] tile, optimized, flip;
    private static final int[][] adj =
            {{-1, 0}, {0, -1}, {0, 0}, {0, 1}, {1, 0}};

    public FlipTile(int[][] tile) {
        this.tile = tile;
        M = tile.length;
        N = tile[0].length;
        optimized = new int[M][N];
        flip = new int[M][N];
    }

    // 0: white, 1: black
    private int getColor(int x, int y) {
        int color = tile[x][y];
        for (int[] d : adj)
            try {
                color += flip[x + d[0]][y + d[1]];
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
        return color % 2;
    }

    private OptionalInt minOp() {
        // from 2nd row
        IntStream.range(1, M).forEach(i ->
                IntStream.range(0, N).forEach(j ->
                        // flip if the above is black
                        flip[i][j] = getColor(i - 1, j)));
        // if last row is not all white
        if (IntStream.range(0, N).anyMatch(j -> getColor(M - 1, j) != 0))
            return OptionalInt.empty();
        // count flip
        return OptionalInt.of(
                Arrays.stream(flip).flatMapToInt(Arrays::stream).sum());
    }

    String solve() {
        OptionalInt result = OptionalInt.empty();
        // try all pattern of top row
        for (int i = 0; i < 1 << N; i++) {
            final int I = i;
            for (int[] row : flip) Arrays.fill(row, 0);
            Arrays.setAll(flip[0], j -> I >> (N - 1 - j) & 1);
            OptionalInt min = minOp();
            if (min.isPresent() &&
                    (!result.isPresent()
                            || result.getAsInt() > min.getAsInt())) {
                result = min;
                for (int r = 0; r < M; r++)
                    System.arraycopy(flip[r], 0, optimized[r], 0, N);
            }
        }
        if (result.isPresent())
            return Arrays.stream(optimized).map(row ->
                    Arrays.stream(row)
                          .mapToObj(Integer::toString)
                          .collect(Collectors.joining(" ")))
                         .collect(Collectors.joining("\n"));
        else return "IMPOSSIBLE";
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int m = scanner.nextInt();
            int n = scanner.nextInt();
            int[][] tile = new int[m][n];
            for (int[] row : tile)
                Arrays.setAll(row, x -> scanner.nextInt());
            FlipTile flipTile = new FlipTile(tile);
            System.out.println(flipTile.solve());
        }
    }
}
