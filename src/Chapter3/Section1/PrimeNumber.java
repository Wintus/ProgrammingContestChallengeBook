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
    private static boolean[] isPrime;

    static int sieve(int n) {
        assert n > 2;
        prime = new ArrayList<>(n / 4);
        isPrime = new boolean[n + 1];
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

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            System.out.println(sieve(n));
            System.out.println(prime);
            System.out.println(Arrays.toString(isPrime));
        }
    }
}
