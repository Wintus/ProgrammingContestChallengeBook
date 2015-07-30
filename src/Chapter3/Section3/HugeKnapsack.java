package Chapter3.Section3;

import Library.Lookup;

import java.util.*;

/**
 * Binary Search.
 * Created by Yuya on 2015/07/29.
 */
public class HugeKnapsack {
    private final int W, N;
    private final int[][] items; // {{weight, value}}

    public HugeKnapsack(int w, int[][] items) {
        W = w;
        this.items = items;
        N = items.length;
    }

    private class Item implements Comparable<Item> {
        final int weight, value;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(@SuppressWarnings("NullableProblems") Item item) {
            int r = Integer.compare(this.weight, item.weight);
            if (r == 0)
                return Integer.compare(this.value, item.value);
            else
                return r;
        }

        @Override
        public String toString() {
            return "weight: " + weight + ", value: " + value;
        }
    }

    int solve() {
        List<Item> ps = new ArrayList<>(1 << (N / 2));
        int n_2 = N / 2;
        for (int i = 0; i < 1 << n_2; i++) {
            int sumW = 0, sumV = 0;
            for (int j = 0; j < n_2; j++) {
                if ((i >> j & 1) == 1) {
                    sumW += items[j][0];
                    sumV += items[j][1];
                }
            }
            ps.add(new Item(sumW, sumV));
        }
        // remove useless: valueless yet heavier
        Collections.sort(ps);
        for (int i = 1; i < ps.size(); i++) // w, v > 1
            for (int j = i + 1; j < ps.size(); j++)
                if (ps.get(i).value >= ps.get(j).value)
                    ps.remove(j--);
        // enumerate all the rest
        int result = 0;
        for (int i = 0; i < 1 << (N - n_2); i++) {
            int sumW = 0, sumV = 0;
            for (int j = 0; j < N - n_2; j++)
                if ((i >> j & 1) == 1) {
                    sumW += items[n_2 + j][0];
                    sumV += items[n_2 + j][1];
                }
            if (sumW <= W) {
                int index = Lookup.lowerBound(ps,
                        new Item(W - sumW, Integer.MAX_VALUE)) - 1;
                result = Math.max(result, sumV + ps.get(index).value);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int[][] items = new int[n][2];
            for (int[] item : items)
                Arrays.setAll(item, x -> scanner.nextInt());
            int w = scanner.nextInt();
            System.out.println(new HugeKnapsack(w, items).solve());
        }
    }
}
