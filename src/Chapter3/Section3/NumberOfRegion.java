package Chapter3.Section3;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Coordinate Compression.
 * Created by Yuya on 2015/07/30.
 */
public class NumberOfRegion {
    private final int W, H, N;
    private final int[] X1, X2, Y1, Y2;
    private boolean[][] filled;
    private final int[][] adj = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public NumberOfRegion(int w, int h, int n,
                          int[] x1, int[] x2, int[] y1, int[] y2) {
        W = w;
        H = h;
        N = n;
        this.X1 = x1;
        this.X2 = x2;
        this.Y1 = y1;
        this.Y2 = y2;
    }

    private int compress(int[] x1, int[] x2, int w) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            for (int d = -1; d <= 1; d++) {
                int _x1 = x1[i] + d, _x2 = x2[i] + d;
                if (1 <= _x1 && _x1 <= w) set.add(_x1);
                if (1 <= _x2 && _x2 <= w) set.add(_x2);
            }
        }
        List<Integer> xs = new ArrayList<>(set);
        Collections.sort(xs);
        Arrays.setAll(x1, i -> xs.indexOf(x1[i]));
        Arrays.setAll(x2, i -> xs.indexOf(x2[i]));
        return xs.size();
    }

    int solve() {
        int w = compress(X1, X2, W);
        int h = compress(Y1, Y2, H);
        filled = new boolean[h][w];
        for (boolean[] row : filled) Arrays.fill(row, false);
        // fill the lines
        for (int i = 0; i < N; i++)
            for (int y = Y1[i]; y <= Y2[i]; y++)
                for (int x = X1[i]; x <= X2[i]; x++)
                    filled[y][x] = true;
        // count the regions
        int count = 0;
        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                if (filled[y][x]) continue;
                else ++count;
                // BFS
                Queue<Point> queue = new LinkedList<>();
                queue.add(new Point(x, y));
                while (!queue.isEmpty()) {
                    int _x = queue.peek().x, _y = queue.peek().y;
                    queue.remove();
                    for (int[] d : adj) {
                        int dx = _x + d[0], dy = _y + d[1];
                        try {
                            if (filled[dy][dx]) continue;
                            queue.add(new Point(dx, dy));
                            filled[dy][dx] = true;
                        } catch (ArrayIndexOutOfBoundsException ignored) {
                        }
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int w = scanner.nextInt();
            int h = scanner.nextInt();
            int n = scanner.nextInt();
            int[] x1 = new int[n];
            int[] x2 = new int[n];
            int[] y1 = new int[n];
            int[] y2 = new int[n];
            Arrays.setAll(x1, i -> scanner.nextInt());
            Arrays.setAll(x2, i -> scanner.nextInt());
            Arrays.setAll(y1, i -> scanner.nextInt());
            Arrays.setAll(y2, i -> scanner.nextInt());
            NumberOfRegion region =
                    new NumberOfRegion(w, h, n, x1, x2, y1, y2);
            System.out.println(region.solve());
        }
    }
}
