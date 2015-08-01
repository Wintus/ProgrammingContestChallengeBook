package Chapter3.Section4;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * BIT.
 * Created by Yuya on 2015/08/01.
 */
public class BinaryIndexedTree {
    private final int N;
    private final int[] bit;

    public BinaryIndexedTree(int n) {
        N = n;
        bit = new int[N + 1]; // initially zero
    }

    public static int lastBit(int i) {
        return i & -i;
    }

    void init() {
        Arrays.fill(bit, 0);
    }

    /**
     * sum of 1 to i.
     *
     * @param i index.
     * @return sequential sum.
     */
    int sum(int i) {
        int sum = 0;
        while (i > 0) {
            sum += bit[i];
            i -= lastBit(i); // subtracts the last bit
        }
        return sum;
    }

    int sumStream(int index) {
        return IntStream.iterate(index, i -> i - lastBit(i))
                        .limit(N)
                        .filter(i -> i > 0)
                        .map(i -> bit[i])
                        .sum();
    }

    /**
     * add n at i.
     *
     * @param i index.
     * @param n number to be add.
     */
    void add(int i, int n) {
        while (i <= N) {
            bit[i] += n;
            i += lastBit(i); // add the last bit
        }
    }

    void addStream(int index, int n) {
        IntStream.iterate(index, i -> i + lastBit(i))
                 .limit(N)
                 .filter(i -> i <= N)
                 .forEach(i -> bit[i] += n);
    }
}
