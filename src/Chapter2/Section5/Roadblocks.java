package Chapter2.Section5;

import java.util.ArrayList;

/**
 * Graph algorithm.
 * Created by Yuya on 2015/07/22.
 */
public class Roadblocks {
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

    private int N, R;
    private ArrayList<Edge> edges;

    public Roadblocks(int n, int r, ArrayList<Edge> edges) {
        N = n;
        R = r;
        this.edges = edges;
    }
}
