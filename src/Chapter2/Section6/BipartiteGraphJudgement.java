package Chapter2.Section6;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Graph.
 * Created by Yuya on 2015/07/15.
 */
public class BipartiteGraphJudgement {
    private final ArrayList<ArrayList<Integer>> graph;
    private final int[] color;
    private final int V;

    public BipartiteGraphJudgement(ArrayList<ArrayList<Integer>> graph) {
        this.graph = graph;
        V = graph.size();
        color = new int[V];
    }

    /**
     * color: -1, 1
     *
     * @param v vertex.
     * @param c color.
     * @return true if unable to paint all adj v.
     */
    private boolean dfs(int v, int c) {
        color[v] = c;
        for (Integer adj : graph.get(v)) {
            if (color[adj] == c) return true;
            if (color[adj] == 0 && dfs(adj, -c)) return true;
        }
        return false;
    }

    boolean solve() {
        for (int i = 0; i < V; i++)
            if (color[i] == 0)
                if (dfs(i, 1))
                    return false;
        return true;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = Integer.parseInt(scanner.nextLine());
            ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
            for (ArrayList<Integer> adj : graph)
                for (String s : scanner.nextLine().split(" "))
                    adj.add(Integer.parseInt(s));
            boolean result = new BipartiteGraphJudgement(graph).solve();
            System.out.println(result ? "Yes" : "No");
        }
    }
}
