package Chapter2.Section4;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Using Union-Find Tree.
 * 3 types of animal: A, B, C.
 * Information:
 * Type 1: x and y is the same type.
 * Type 2: x eats y.
 * Created by Yuya on 2015/07/14.
 */
class FoodChain {
    private final int N;
    private final int[][] information; // {{type, x, y}}
    private UnionFindTree data;

    public FoodChain(int n, int[][] information) {
        N = n;
        this.information = information;
    }

    class UnionFindTree {
        final int[] parents, ranks;

        public UnionFindTree(int n) {
            parents = new int[n];
            ranks = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
                ranks[i] = 0;
            }
        }

        int find(int i) {
            if (parents[i] == i) return i;
            else return parents[i] = find(parents[i]);
        }

        void unite(int x, int y) {
            x = find(x);
            y = find(y);
            if (x == y) return;
            if (ranks[x] < ranks[y]) {
                parents[x] = y;
            } else {
                parents[y] = x;
                if (ranks[x] == ranks[y]) ranks[x]++;
            }
        }

        boolean isSame(int x, int y) {
            return find(x) == find(y);
        }
    }

    int solve() {
        data = new UnionFindTree(N * 3); // x-A: x, x-B: x + N, x-C: x + 2 * N
        int answer = 0;
        for (int[] i : information) {
            int type = i[0];
            int x = i[1] - 1, y = i[2] - 1; // to index
            // invalid index
            if (x < 0 || N <= x || y < 0 || N <= y) {
                ++answer;
                continue;
            }
            // invalid: A = B, A = C
            if (type == 1) {
                if (data.isSame(x, y + N) || data.isSame(x, y + 2 * N))
                    ++answer;
                else
                    IntStream.rangeClosed(0, 2)
                             .forEach(n -> data.unite(x + n * N, y + n * N));
            } else {
                // invalid: A eats A, A eats C
                if (data.isSame(x, y) || data.isSame(x, y + 2 * N))
                    ++answer;
                else
                    IntStream.rangeClosed(0, 2)
                             .forEach(n -> data.unite(x + n * N, y + n * N));
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            int[][] info = new int[k][3];
            IntStream.range(0, k).forEach(i ->
                    Arrays.setAll(info[i], x -> scanner.nextInt()));
            System.out.println(new FoodChain(n, info).solve());
        }
    }
}
