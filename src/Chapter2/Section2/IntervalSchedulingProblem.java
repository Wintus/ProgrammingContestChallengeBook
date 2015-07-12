package Chapter2.Section2;

import java.util.Arrays;

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
}
