package Chapter2.Section6;

import java.util.ArrayList;

/**
 * Graph.
 * Created by Yuya on 2015/07/15.
 */
public class BipartiteGraphJudgement {
    private final ArrayList<Integer>[] graph;
    private final int[] color;
    private final int V;

    public BipartiteGraphJudgement(ArrayList<Integer>[] graph) {
        this.graph = graph;
        V = graph.length;
        color = new int[V];
    }

    // color: -1, 1
    private boolean dfs(int v, int c) {
        color[v] = c;
        for (Integer adj : graph[v]) {
            if (color[adj] == c) return false;
            if (color[adj] == 0 && !dfs(adj, -c)) return false;
        }
        return true;
    }

    boolean solve() {
        for (int i = 0; i < V; i++) {
            if (color[i] == 0)
                if (!dfs(i, 1))
                    return false;
        }
        return true;
    }
}
