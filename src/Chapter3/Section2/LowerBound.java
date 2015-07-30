package Chapter3.Section2;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * Binary Search.
 * Find the smallest index that a[i] >= k from a increasing number line.
 * Created by Yuya on 2015/07/26.
 */
public class LowerBound {
    private final int[] ns;
    private final int k;

    public LowerBound(int[] ns, int k) {
        this.ns = ns;
        this.k = k;
    }

    static double lowerBound(Predicate<Double> predicate, double precision) {
        double lb = 0, mid = 0, ub = 1_000_000;
        while (ub - lb > precision) {
            mid = (lb + ub) / 2;
            if (predicate.test(mid))
                lb = mid;
            else
                ub = mid;
        }
        return mid;
    }

    int solve() {
        int lowerBound = -1, upperBound = ns.length - 1, middle;
        while (upperBound - lowerBound > 1) {
            middle = (lowerBound + upperBound) / 2;
            if (ns[middle] >= k)
                upperBound = middle; // solution in (lb, mid]
            else
                lowerBound = middle; // solution in (mid, ub]
        }
        return upperBound;
    }

    int solve1() {
        int lowerBound = 0, upperBound = ns.length - 1, middle = 0;
        while (lowerBound <= upperBound) {
            middle = (upperBound + lowerBound) / 2;
            if (ns[middle] >= k)
                upperBound = middle - 1; // solution is in [lb, mid)
            else
                lowerBound = middle + 1; // solution is in (mid, ub]
        }
        return middle;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int[] ns = new int[n];
            Arrays.setAll(ns, x -> scanner.nextInt());
            int k = scanner.nextInt();
            LowerBound lowerBound = new LowerBound(ns, k);
            System.out.println(lowerBound.solve());
            System.out.println(lowerBound.solve1());
        }
    }
}
