package Chapter2.Section5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Use MST of Kruskal.
 * Created by Yuya on 2015/07/23.
 */
public class Conscription {
    static class Edge {

        final int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

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
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) return;
            if (ranks[rootX] < ranks[rootY]) {
                parents[rootX] = rootY;
            } else {
                parents[rootY] = rootX;
                if (ranks[rootX] == ranks[rootY]) ranks[rootX]++;
            }
        }

        boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }

    }

    private final int N;
    private final int M;
    private final int V;
    private ArrayList<Edge> edges;
    private ArrayList<Edge> mst;

    public Conscription(int n, int m, ArrayList<Edge> edges) {
        N = n;
        M = m;
        V = N + M;
        this.edges = edges;
    }

    /**
     * Get MST in O(E log V) using UnionFindTree.
     *
     * @return total cost of the MST.
     */
    private int kruskal() {
        mst = new ArrayList<>();
        Collections.sort(edges, (e0, e1) -> Integer.compare(e0.cost, e1.cost));
        UnionFindTree uft = new UnionFindTree(V);
        edges.forEach(edge -> {
            if (!uft.isConnected(edge.from, edge.to)) {
                uft.unite(edge.from, edge.to);
                mst.add(edge);
            }
        });
        return mst.stream().mapToInt(edge -> edge.cost).sum();
    }

    int solve() {
        edges = edges.stream().map(edge ->
                new Edge(edge.from, N + edge.to, -edge.cost))
                     .collect(Collectors.toCollection(ArrayList::new));
        return 10000 * (N + M) + kruskal();
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int r = scanner.nextInt();
            ArrayList<Edge> edges = new ArrayList<>(r);
            for (int i = 0; i < r; i++)
                edges.add(new Edge(
                        scanner.nextInt(),
                        scanner.nextInt(),
                        scanner.nextInt()));
            Conscription conscription = new Conscription(n, m, edges);
            System.out.println(conscription.solve());
        }
    }
}
