package Chapter3.Section5;

import Library.IntMatrix2d;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Use adjacent matrix
 * Created by Yuya on 2015/08/12.
 */
public class TotalNumberOfPathOfLengthKInGraph {
    private final int K;
    private final IntMatrix2d graph;

    public TotalNumberOfPathOfLengthKInGraph(int k, int[][] graph) {
        K = k;
        this.graph = new IntMatrix2d(graph);
    }

    int solve() {
        final int[][] matrix = graph.power(K).getMatrix();
        return Arrays.stream(matrix).mapToInt(row ->
                (int) Arrays.stream(row).filter(n -> n == K).count()).sum();
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = 4;
            int k = scanner.nextInt();
            int[][] paths = {{1, 2}, {1, 3}, {2, 3}, {3, 4}, {4, 1}};
            int[][] graph = new int[n][n];
            for (int[] path : paths) graph[path[0] - 1][path[1] - 1] = 1;
            System.out.println(new TotalNumberOfPathOfLengthKInGraph(k, graph).solve());
        }
    }
}
