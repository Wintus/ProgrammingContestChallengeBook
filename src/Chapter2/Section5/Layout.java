package Chapter2.Section5;

import java.util.Arrays;

/**
 * Use Bellman Ford.
 * Created by Yuya on 2015/07/23.
 */
public class Layout {
    private int N;
    private int[] distances;
    private int[][] likes, hates; // {{from, to, distances}}
    private static final int INF = Integer.MAX_VALUE / 2;

    public Layout(int n, int[][] likes, int[][] hates) {
        N = n;
        this.likes = likes;
        this.hates = hates;
        distances = new int[N];
    }

    int solve() {
        Arrays.fill(distances, INF);
        distances[0] = 0;
        //Bellman Ford
        for (int k = 0; k < N; k++) {
            // i -> (i + 1): 0
            for (int i = 0; i + 1 < N; i++)
                if (distances[i + 1] < INF)
                    distances[i] = Math.min(distances[i], distances[i + 1]);
            // like.from -> like.to: like.distance
            for (int[] like : likes)
                if (distances[like[0] - 1] < INF)
                    distances[like[1] - 1] = Math.min(
                            distances[like[1] - 1],
                            distances[like[0] - 1] + like[2]);
            // hate.to -> hate.from: -hate.distance
            for (int[] hate : hates)
                if (distances[hate[1] - 1] < INF)
                    distances[hate[0] - 1] = Math.min(
                            distances[hate[0] - 1],
                            distances[hate[1] - 1] - hate[2]);
        }
        if (distances[0] < 0)
            return -1; // negative loop exists
        else if (distances[N - 1] == INF)
            return -2;
        else
            return distances[N - 1];
    }
}
