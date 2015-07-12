package Chapter2.Section1;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Using breadth first search.
 * Created by Yuya on 2015/07/11.
 */
class Maze {
    private static final char WALL = '#';
    private static final char START = 'S';
    private static final char GOAL = 'G';
    private static final int INF = Integer.MAX_VALUE / 4;
    private static final int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private char[][] maze;
    private int[][] distances;
    private Point start;
    private Point goal;

    public Maze(char[][] maze) {
        this.maze = maze;
        int n = maze.length;
        int m = maze[0].length;
        distances = new int[n][m];
        // find the start and the goal
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (maze[i][j] == START)
                    start = new Point(i, j);
                else if (maze[i][j] == GOAL)
                    goal = new Point(i, j);
        // initialize all positions' distance as infinity
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                distances[i][j] = INF;
    }

    /**
     * Find the distance from start to goal.
     *
     * @return distance.
     */
    private int bfs() {
        Queue<Point> queue = new LinkedList<>();
        // set start position's distance as 0
        queue.add(start);
        distances[start.x][start.y] = 0;

        while (queue.size() > 0) {
            Point here = queue.poll();
            if (here.equals(goal))
                break; // reached the goal
            for (int[] direction : directions) {
                Point there = here.getLocation();
                there.move(direction[0], direction[1]);
                try {
                    if (maze[there.x][there.y] != WALL &&
                            distances[there.x][there.y] == INF) {
                        queue.add(there);
                        distances[there.x][there.y] =
                                distances[here.x][here.y] + 1;
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }
        }
        return distances[goal.x][goal.y];
    }

    /**
     * Find the distance from start to goal.
     *
     * @return distance.
     */
    int solve() {
        return bfs();
    }
}
