package Chapter2.Section5;

import java.util.ArrayList;

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

    private int N, M;
    private ArrayList<Edge> edges;

    public Conscription(int n, int m, ArrayList<Edge> edges) {
        N = n;
        M = m;
        this.edges = edges;
    }
}
