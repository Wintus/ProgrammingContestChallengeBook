package Chapter2.Section5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

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
    }

    private int N;
    private ArrayList<Edge> edges;
    private static final int INF = Integer.MAX_VALUE / 2;

    public Roadblocks(int n, ArrayList<Edge> edges) {
        N = n;
        this.edges = edges;
    }

    int solve() {
        int[] distance = new int[N];
        int[] distance2 = new int[N];
        PriorityQueue<Edge> queue = new PriorityQueue<>((e0, e1) ->
                Integer.compare(e0.cost, e1.cost));
        Arrays.fill(distance, INF);
        Arrays.fill(distance2, INF);
        distance[0] = 0;
        queue.add(new Edge(-1, 0, 0));

        while (!queue.isEmpty()) {
            Edge poll = queue.poll();
            int v = poll.to;
            if (distance2[v] < poll.cost) continue;
            edges.stream().filter(edge -> edge.from == v)
                 .forEach(edge -> {
                     int d2 = poll.cost + edge.cost;
                     if (distance[edge.to] > d2) {
                         // swap
                         int dummy = distance[edge.to];
                         distance[edge.to] = d2;
                         d2 = dummy;
                         queue.add(new Edge(-1, edge.to, distance[edge.to]));
                     }
                     if (distance2[edge.to] > d2 && distance[edge.to] < d2) {
                         distance2[edge.to] = d2;
                         queue.add(new Edge(-1, edge.to, distance2[edge.to]));
                     }
                 });
        }

        return distance2[distance2.length - 1];
    }
}
