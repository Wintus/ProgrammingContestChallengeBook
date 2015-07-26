package Chapter3.Section1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Sieve of Eratosthenes. O(n log log n)
 * Created by Yuya on 2015/07/25.
 */
public class PrimeNumber {

    private static ArrayList<Integer> prime;

    static int sieve(int n) {
        assert n > 2;
        prime = new ArrayList<>(Math.max(n / 2, 8));
        boolean[] isPrime = new boolean[n + 1];
        int p = 0;
        Arrays.fill(isPrime, true);
        Arrays.fill(isPrime, 0, 2, false);
        for (int i = 2; i <= n; i++)
            if (isPrime[i]) {
                prime.add(i);
                ++p;
                for (int j = 2 * i; j <= n; j += i) isPrime[j] = false;
            }
        return p;
    }

    static int segmentSieve(long a, long b) {
        // i is prime = isPrime[i - a]
        boolean[] _isPrime = new boolean[(int) Math.sqrt(b) + 1];
        boolean[] isPrime = new boolean[(int) (b - a)];
        Arrays.fill(_isPrime, true);
        Arrays.fill(isPrime, true);
        for (int i = 2; (long) i * i < b; i++)
            if (_isPrime[i]) {
                // sieve [2, b^(1/2) )
                for (int j = 2 * i; (long) j * j < b; j += i)
                    _isPrime[j] = false;
                // sieve [a, b)
                for (long j = Math.max((a + i - 1) / i, 2) * i; j < b; j += i)
                    isPrime[((int) (j - a))] = false;
            }
        int p = 0;
        for (boolean b1 : isPrime) if (b1) ++p;
        return p;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            System.out.println(sieve(n));
            System.out.println(prime);
            System.out.println();

            long a = scanner.nextLong();
            long b = scanner.nextLong();
            System.out.println(segmentSieve(a, b));
        }
    }
}
