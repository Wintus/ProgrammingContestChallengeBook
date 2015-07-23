package Chapter2.Section5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Spanning Tree problem. Graph is connected.
 * Created by Yuya on 2015/07/18.
 */
public class MinimumSpanningTree {
    class Edge {
        final int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return from + " -> " + to + ": " + cost;
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

    private ArrayList<Edge> edges;
    private ArrayList<Edge> mst;
    private boolean[] used;
    private int V;

    public MinimumSpanningTree(ArrayList<Edge> edges, int v) {
        this.edges = edges;
        V = v;
        used = new boolean[V];
    }

    public MinimumSpanningTree() {
        edges = new ArrayList<>();
        initialize();
        V = 7;
        used = new boolean[V];
    }

    private void initialize() {
        edges.add(new Edge(0, 2, 1));
        edges.add(new Edge(1, 2, 2));
        edges.add(new Edge(1, 4, 10));
        edges.add(new Edge(2, 0, 1));
        edges.add(new Edge(2, 1, 2));
        edges.add(new Edge(2, 3, 3));
        edges.add(new Edge(2, 5, 7));
        edges.add(new Edge(3, 2, 3));
        edges.add(new Edge(3, 5, 1));
        edges.add(new Edge(3, 6, 5));
        edges.add(new Edge(4, 1, 10));
        edges.add(new Edge(4, 5, 5));
        edges.add(new Edge(5, 2, 7));
        edges.add(new Edge(5, 3, 1));
        edges.add(new Edge(5, 4, 5));
        edges.add(new Edge(5, 6, 8));
        edges.add(new Edge(6, 3, 5));
        edges.add(new Edge(6, 5, 8));
    }

    /**
     * Get MST in O(E log V) using PriorityQueue.
     *
     * @return sum of all edges' costs.
     */
    int prim() {
        PriorityQueue<Edge> queue = new PriorityQueue<>((e0, e1) ->
                Integer.compare(e0.cost, e1.cost));
        mst = new ArrayList<>();
        Arrays.fill(used, false);
        used[0] = true;
        edges.stream().filter(edge -> edge.from == 0).forEach(queue::add);
        while (!queue.isEmpty()) {
            Edge poll = queue.poll();
            if (used[poll.to]) continue;
            mst.add(poll);
            used[poll.to] = true;
            edges.stream().filter(edge -> edge.from == poll.to)
                 .forEach(queue::add);
        }
        return mst.stream().mapToInt(edge -> edge.cost).sum();
    }

    /**
     * Get MST in O(E log V) using UnionFindTree.
     *
     * @return total cost of the MST.
     */
    int kruskal() {
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

    public static void main(String[] args) {
        MinimumSpanningTree mst = new MinimumSpanningTree();
        System.out.println(mst.prim());
        System.out.println(mst.mst);
        System.out.println();
        System.out.println(mst.kruskal());
        System.out.println(mst.mst);
    }
}
