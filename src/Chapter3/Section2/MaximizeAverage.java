package Chapter3.Section2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * Binary Search. Max Average.
 * Created by Yuya on 2015/07/26.
 */
public class MaximizeAverage {
    private final int k;
    private final int[][] items; // {{w, v}}
    private final double[] a;

    public MaximizeAverage(int k, int[][] items) {
        this.k = k;
        this.items = items;
        a = new double[items.length];
    }

    private boolean condition(double x) {
        Arrays.setAll(a, i -> items[i][1] - x * items[i][0]);
        ArrayList<Double> ds = new ArrayList<>(a.length);
        for (double d : a) ds.add(d);
        Collections.sort(ds, Collections.reverseOrder());
        double sum = ds.stream().limit(k).mapToDouble(d -> d).sum();
        return sum >= 0;
    }

    double solve() {
        return LowerBound.lowerBound(this::condition, .01);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            int[][] items = new int[n][2];
            for (int[] item : items)
                Arrays.setAll(item, x -> scanner.nextInt());
            System.out.printf("%.2f\n", new MaximizeAverage(k, items).solve());
        }
    }
}
