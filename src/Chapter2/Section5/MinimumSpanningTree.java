package Chapter2.Section5;

import java.util.ArrayList;
import java.util.Arrays;
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
    }

    private ArrayList<Edge> edges;
    private int[] min_cost;
    private boolean[] used;
    private int V;
    private static final int INF = Integer.MAX_VALUE / 2;

    public MinimumSpanningTree(ArrayList<Edge> edges, int v) {
        this.edges = edges;
        V = v;
        min_cost = new int[V];
        used = new boolean[V];
    }

    public MinimumSpanningTree() {
        edges = new ArrayList<>();
        initialize();
        V = 7;
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

    int prim() {
        PriorityQueue<Edge> queue =
                new PriorityQueue<>(
                        (o1, o2) -> Integer.compare(
                                min_cost[o1.from], min_cost[o2.from]));
        queue.addAll(edges);
        Arrays.fill(min_cost, INF);
        Arrays.fill(used, false);
        min_cost[0] = 0;
        int result = 0;
        while (!queue.isEmpty()) {
            Edge poll = queue.poll();
            int vertex = poll.from;
            used[vertex] = true;
            result += min_cost[vertex];
            edges.forEach(edge ->
                    min_cost[edge.to] = Math.min(min_cost[edge.to], edge.cost));
        }
        return result;
    }

    public static void main(String[] args) {
        MinimumSpanningTree mst = new MinimumSpanningTree();
        System.out.println(mst.prim());
    }
}
