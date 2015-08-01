package Chapter3.Section4;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Use BIT (Binary Indexed Tree). O(n log n).
 * Created by Yuya on 2015/08/01.
 */
public class BubbleSortSwapCount {
    private final int[] ns;
    private final BinaryIndexedTree bit;

    public BubbleSortSwapCount(int[] ns) {
        this.ns = ns;
        bit = new BinaryIndexedTree(ns.length);
    }

    /**
     * count = inversion number = number of (i, j): i < j & a_i > a_j.
     * sum[j] = number of is: i < j & a_i <= a_j.
     *
     * @return swap count.
     */
    long solve() {
        long count = 0;
        for (int j = 0; j < ns.length; j++) {
            count += j - bit.sum(j);
            bit.add(ns[j], 1);
        }
        return count;
    }

    long solveStream() {
        return IntStream.range(0, ns.length)
                        .mapToLong(j -> {
                            int count = j - bit.sum(j);
                            bit.add(ns[j], 1);
                            return count;
                        }).sum();
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int[] ns = new int[n];
            Arrays.setAll(ns, x -> scanner.nextInt());
            System.out.println(new BubbleSortSwapCount(ns).solve());
        }
    }
}
