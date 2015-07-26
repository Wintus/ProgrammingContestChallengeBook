package Chapter3.Section1;

import Library.Timer;

import java.math.BigInteger;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

/**
 * Math. Carmichael Number Detection.
 * Created by Yuya on 2015/07/26.
 */
public class CarmichaelNumber {
    static boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++)
            if (n % i == 0) return false;
        return n != 1;
    }

    /**
     * power function of Repeated Squares Algorithm.
     *
     * @param x   base.
     * @param n   power.
     * @param mod modulo.
     * @return x^n modulo mod.
     */
    static long modPow(long x, long n, long mod) {
        long result = 1;
        while (n > 0) {
            if ((n & 1) == 1) result = result * x % mod;
            x = x * x % mod;
            n >>= 1;
        }
        return result;
    }

    static long modPow1(long x, long n, long mod) {
        if (n == 0) return 1;
        long result = modPow1(x * x % mod, n / 2, mod);
        if ((n & 1) == 1) result = result * x % mod;
        return result;
    }

    static BigInteger power(BigInteger x, BigInteger n) {
        BigInteger result = BigInteger.ONE;
        while (n.compareTo(BigInteger.ZERO) > 0) {
            if (n.and(BigInteger.ONE).equals(BigInteger.ONE))
                result = result.multiply(x);
            x = x.pow(2);
            n = n.shiftRight(1);
        }
        return result;
    }

    private static boolean
    _solve(int n, BiFunction<Integer, Integer, Long> f) {
        return !isPrime(n) && IntStream.range(2, n)
                                       .anyMatch(x -> x == f.apply(x, n));
    }

    static boolean solve(int n) {
        return _solve(n, (X, N) -> modPow(X, N, N));
    }

    static boolean solve1(int n) {
        return _solve(n, (X, N) -> modPow1(X, N, N));
    }

    private static boolean
    _solveBig(int n, BiFunction<BigInteger, BigInteger, BigInteger> f) {
        BigInteger N = BigInteger.valueOf(n);
        return !isPrime(n) && IntStream.range(2, n)
                                       .mapToObj(BigInteger::valueOf)
                                       .anyMatch(X -> X.equals(f.apply(X, N)));
    }

    static boolean solveBig(int n) {
        return _solveBig(n, (X, N) -> power(X, N).mod(N));
    }

    static boolean solveBig1(int n) {
        return _solveBig(n, (X, N) -> X.pow(n).mod(N));
    }

    static boolean solveBig0(int n) {
        return _solveBig(n, (X, N) -> X.modPow(N, N));
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            System.out.println(solve(n) ? "Yes" : "No");
            System.out.println(solve1(n) ? "Yes" : "No");
            System.out.println(solveBig(n) ? "Yes" : "No");
            System.out.println(solveBig1(n) ? "Yes" : "No");
            System.out.println(solveBig0(n) ? "Yes" : "No");
            System.out.println();

            // show in É s
            Timer<Object> timer = new Timer<>();
            long time;
            time = timer.run(null, x ->
                    System.out.print(solve(n) ? "Yes" : "No"));
            System.out.println(" " + (time / 1000) + "\u00B5s");
            time = timer.run(null, x ->
                    System.out.print(solve1(n) ? "Yes" : "No"));
            System.out.println(" " + (time / 1000) + "\u00B5s");
            time = timer.run(null, x ->
                    System.out.print(solveBig(n) ? "Yes" : "No"));
            System.out.println(" " + (time / 1000) + "\u00B5s");
            time = timer.run(null, x ->
                    System.out.print(solveBig1(n) ? "Yes" : "No"));
            System.out.println(" " + (time / 1000) + "\u00B5s");
            time = timer.run(null, x ->
                    System.out.print(solveBig0(n) ? "Yes" : "No"));
            System.out.println(" " + (time / 1000) + "\u00B5s");
        }
    }
}
