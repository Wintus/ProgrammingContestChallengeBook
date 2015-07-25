package Chapter3.Section1;

import java.util.*;

/**
 * Math.
 * Created by Yuya on 2015/07/25.
 */
public class PrimalityTest {
    static boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++)
            if (n % i == 0) return false;
        return n != 1;
    }

    static Set<Integer> divisor(int n) {
        Set<Integer> result = new HashSet<>();
        for (int i = 2; i * i <= n; i++)
            if (n % i == 0) {
                result.add(i);
                result.add(n / i);
            }
        return result;
    }

    static Map<Integer, Integer> primeFactor(int n) {
        HashMap<Integer, Integer> result = new HashMap<>();
        for (int i = 2; i * i <= n; i++)
            while (n % i == 0) {
                // r[n]++
                result.put(i, result.getOrDefault(i, 0) + 1);
                n /= i;
            }
        if (n != 1) result.put(n, 1);
        return result;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            System.out.println(isPrime(n));
            System.out.println(divisor(n));
            ArrayList<Integer> ds = new ArrayList<>(divisor(n));
            ds.sort(Integer::compare);
            System.out.println(ds);
            System.out.println(primeFactor(n));
        }
    }
}
