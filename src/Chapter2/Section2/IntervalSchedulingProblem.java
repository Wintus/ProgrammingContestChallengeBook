package Chapter2.Section2;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Using greedy algorithm.
 * Created by Yuya on 2015/07/12.
 */
class IntervalSchedulingProblem {
    private final int[][] schedule; // {{start, end}}

    public IntervalSchedulingProblem(int[][] schedule) {
        this.schedule = schedule;
    }

    int solve() {
        Arrays.sort(schedule, (a1, a2) -> Integer.compare(a1[1], a2[1]));
        int n = 0, end = 0;
        for (int[] aSchedule : schedule) {
            if (end < aSchedule[0]) {
                ++n;
                end = aSchedule[1];
            }
        }
        return n;
    }

    // @formatter:off
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int[][] schedule = new int[n][2];
            IntStream.range(0, n).forEach(i ->
                IntStream.range(0, 2).forEach(j ->
                    schedule[i][j] = scanner.nextInt()));
            System.out.println(new IntervalSchedulingProblem(schedule).solve());
        }
    }
}
