package Chapter2.Section5;

import java.util.ArrayList;

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
    }

    private ArrayList<Edge> edges;
    private int[] min_cost;
    private boolean[] used;
    private int V;

    public MinimumSpanningTree(ArrayList<Edge> edges, int v) {
        this.edges = edges;
        V = v;
        min_cost = new int[V];
        used = new boolean[V];
    }

    public MinimumSpanningTree() {
        edges = new ArrayList<>();
        initialize();
        min_cost = new int[V];
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
}
