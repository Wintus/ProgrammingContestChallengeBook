package Chapter2.Section4;

/**
 * Using Union-Find Tree.
 * Type 1: x and y is the same type.
 * Type 2: x eats y.
 * Created by Yuya on 2015/07/14.
 */
public class FoodChain {
    int N;
    int[][] information; // {{type, x, y}}

    public FoodChain(int n, int[][] information) {
        N = n;
        this.information = information;
    }

    class UnionFindTree {
        int[] parents, ranks;

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
}
