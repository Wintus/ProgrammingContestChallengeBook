package Chapter2.Section5;

/**
 * Use Bellman Ford.
 * Created by Yuya on 2015/07/23.
 */
public class Layout {
    static class Edge {
        final int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    private int N;
    private int[] distance;
    private int[][] like, hate;

    public Layout(int n, int[][] like, int[][] hate) {
        N = n;
        this.like = like;
        this.hate = hate;
        distance = new int[N];
    }
}
