package Chapter2.Section4;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Using Priority Queue.
 * Created by Yuya on 2015/07/14.
 */
class Expedition {
    private final int L;
    private final int P;
    private final int n;
    private final int[][] stands; // {distance, gas}

    public Expedition(int l, int p, int[][] stands) {
        L = l;
        P = p;
        n = stands.length;
        this.stands = new int[n + 1][2];
        IntStream.range(0, n).forEach(i ->
                IntStream.range(0, 2).forEach(j ->
                        this.stands[i][j] = stands[i][j]));
        this.stands[n] = new int[]{L, 0}; // add goal as the last stand
    }

    int solve() {
        PriorityQueue<Integer> queue =
                new PriorityQueue<>((o1, o2) -> -o1.compareTo(o2));
        int answer = 0, position = 0, tank = P, N = n + 1;
        for (int i = 0; i < N; i++) {
            int d = stands[i][0] - position;
            while (tank - d < 0) {
                if (queue.isEmpty()) return -1;
                tank += queue.poll();
                ++answer;
            }
            tank -= d;
            position = stands[i][0];
            queue.add(stands[i][1]);
        }
        return answer;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int l = scanner.nextInt();
            int p = scanner.nextInt();
            int[][] stand = new int[n][2];
            IntStream.range(0, n).forEach(i ->
                    Arrays.setAll(stand[i], x -> scanner.nextInt()));
            System.out.println(new Expedition(l, p, stand).solve());
        }
    }
}
