package Chapter2.Section5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Graph algorithm.
 * Created by Yuya on 2015/07/22.
 */
public class Roadblocks {
    static class Edge {
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
                     if (d2 < distance[edge.to]) {
                         // swap
                         int dummy = distance[edge.to];
                         distance[edge.to] = d2;
                         d2 = dummy;
                         queue.add(new Edge(-1, edge.to, distance[edge.to]));
                     }
                     if (distance[edge.to] < d2 && d2 < distance2[edge.to]) {
                         distance2[edge.to] = d2;
                         queue.add(new Edge(-1, edge.to, distance2[edge.to]));
                     }
                 });
        }

        return distance2[distance2.length - 1];
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int r = scanner.nextInt();
            ArrayList<Edge> edges = new ArrayList<>(r * 2);
            for (int i = 0; i < r * 2; i++)
                edges.add(new Edge(
                        scanner.nextInt(),
                        scanner.nextInt(),
                        scanner.nextInt()));
/*
            ArrayList<Edge> edges =
                    IntStream.range(0, r * 2).mapToObj(i -> new Edge(
                            scanner.nextInt(),
                            scanner.nextInt(),
                            scanner.nextInt()))
                             .collect(Collectors.toCollection(ArrayList::new));
*/
            Roadblocks roadblocks = new Roadblocks(n, edges);
            System.out.println(roadblocks.solve());
        }
    }
}
