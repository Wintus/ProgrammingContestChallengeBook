package Chapter2.Section2;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * æÃ—~–@ - greedy algorithm
 * Created by Yuya on 2015/07/12.
 */
public class Coin {
    public static final int[] coins = {1, 5, 10, 50, 100, 500};
    private int[] holding = new int[coins.length];
    private int amount;

    public Coin(int[] holding, int amount) {
        this.holding = holding;
        this.amount = amount;
    }

    /**
     * Find the smallest number of coins to pay.
     *
     * @return the minimum number of coins.
     */
    int solve() {
        return IntStream.iterate(coins.length - 1, i -> i - 1).limit(coins.length)
                .map(i -> {
                    int n = Math.min(amount / coins[i], holding[i]);
                    amount -= coins[i] * n;
                    return n;
                }).sum();
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int[] holding = new int[coins.length];
            for (int i = 0; i < coins.length; i++) {
                holding[i] = scanner.nextInt();
            }
            int amount = scanner.nextInt();
            System.out.println(new Coin(holding, amount).solve());
        }
    }
}
