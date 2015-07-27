package Chapter3.Section3;

import java.util.*;

/**
 * ÇµÇ·Ç≠Ç∆ÇËñ@ÅB
 * Created by Yuya on 2015/07/27.
 */
public class ReadingProblem {
    private final int[] a;

    public ReadingProblem(int[] a) {
        this.a = a;
    }

    int solve() {
        int P = a.length;
        Set<Integer> set = new HashSet<>(P);
        for (int i : a) set.add(i);
        int total = set.size();

        int s = 0, t = 0, n = 0, result = P;
        Map<Integer, Integer> count = new HashMap<>();
        while (true) {
            while (t < P && n < total) {
                if (count.getOrDefault(a[t], 0) == 0) ++n;
                count.put(a[t], count.getOrDefault(a[t], 0) + 1);
                ++t;
            }
            if (n < total) break;
            result = Math.min(result, t - s);
            if (count.get(a[s]) - 1 == 0) --n;
            count.put(a[s], count.getOrDefault(a[s], 0) - 1);
            ++s;
        }
        return result;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int p = scanner.nextInt();
            int[] a = new int[p];
            Arrays.setAll(a, x -> scanner.nextInt());
            System.out.println(new ReadingProblem(a).solve());
        }
    }
}
