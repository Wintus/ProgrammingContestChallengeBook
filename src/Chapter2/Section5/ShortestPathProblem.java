package Chapter2.Section5;

import java.util.*;

/**
 * Shortest Path Problem Exercise.
 * Created by Yuya on 2015/07/18.
 */
public class ShortestPathProblem {
    private final ArrayList<Edge> edges;
    private final int[] distance;
    private final int V;
    private final int INF = Integer.MAX_VALUE / 2;

    class Edge {
        final int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    public ShortestPathProblem(ArrayList<Edge> edges, int v) {
        this.edges = edges;
        V = v;
        distance = new int[V];
    }

    public ShortestPathProblem() {
        edges = new ArrayList<>();
        initialize();
//        V = edges.parallelStream().mapToInt(edge -> edge.from).max().getAsInt();
        V = 7;
        distance = new int[V];
    }

    void initialize() {
        edges.add(new Edge(0, 1, 2));
        edges.add(new Edge(0, 2, 5));
        edges.add(new Edge(1, 0, 2));
        edges.add(new Edge(1, 2, 4));
        edges.add(new Edge(1, 3, 6));
        edges.add(new Edge(1, 4, 10));
        edges.add(new Edge(2, 0, 5));
        edges.add(new Edge(2, 1, 4));
        edges.add(new Edge(2, 3, 2));
        edges.add(new Edge(3, 1, 6));
        edges.add(new Edge(3, 2, 2));
        edges.add(new Edge(3, 5, 1));
        edges.add(new Edge(4, 1, 10));
        edges.add(new Edge(4, 5, 3));
        edges.add(new Edge(4, 6, 5));
        edges.add(new Edge(5, 3, 1));
        edges.add(new Edge(5, 4, 3));
        edges.add(new Edge(5, 6, 9));
        edges.add(new Edge(6, 4, 5));
        edges.add(new Edge(6, 5, 9));
    }

    /**
     * find the shortest path by Bellman-Ford method.
     */
    void bellman_ford(int s) {
        Arrays.fill(distance, INF);
        distance[s] = 0;
        while (true) {
            boolean updated = false;
            for (Edge edge : edges) {
                int cost = distance[edge.from] + edge.cost;
                if (distance[edge.from] != INF && distance[edge.to] > cost) {
                    distance[edge.to] = cost;
                    updated = true;
                }
            }
            if (!updated) break;
        }
    }

    boolean detect_negative_loop() {
        Arrays.fill(distance, 0);
        for (int i = 0; i < V; i++) {
            for (Edge edge : edges) {
                int cost = distance[edge.from] + edge.cost;
                if (distance[edge.to] > cost) {
                    distance[edge.to] = cost;
                    if (i == V - 1) return true;
                }
            }
        }
        return false;
    }

    class Pair<K, V> extends HashMap.SimpleImmutableEntry<K, V> {
        public Pair(K key, V value) {
            super(key, value);
        }
    }

    /**
     * for non-negative-cost graph
     *
     * @param start start vertex.
     */
    void dijkstra(int start) {
        PriorityQueue<Pair<Integer, Integer>> queue =
                new PriorityQueue<>((o1, o2) ->
                        Integer.compare(o1.getValue(), o2.getValue()));
        Arrays.fill(distance, INF);
        distance[start] = 0;
        // (v, min_dis)
        queue.add(new Pair<>(start, 0));
        while (!queue.isEmpty()) {
            Map.Entry<Integer, Integer> poll = queue.poll();
            int vertex = poll.getKey();
            if (distance[vertex] < poll.getValue()) continue;
            edges.stream().filter(edge -> edge.from == vertex) // parallel
                 .forEach(edge -> {
                     int cost = distance[vertex] + edge.cost;
                     if (distance[edge.to] > cost) {
                         distance[edge.to] = cost;
                         queue.add(new Pair<>(edge.to, distance[edge.to]));
                     }
                 });
        }
    }

    public static void main(String[] args) {
        ShortestPathProblem spp = new ShortestPathProblem();
        System.out.println("INF = " + spp.INF);
        spp.bellman_ford(0);
        System.out.println(Arrays.toString(spp.distance));
        System.out.println(spp.distance[spp.distance.length - 1]);
        System.out.println("Negative Loop?: " + spp.detect_negative_loop());
        System.out.println();
        spp.dijkstra(0);
        System.out.println(Arrays.toString(spp.distance));
        System.out.println(spp.distance[spp.distance.length - 1]);
    }
}
