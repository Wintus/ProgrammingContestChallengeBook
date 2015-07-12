package Chapter2.Section2;

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
        return IntStream.range(0, coins.length)
                .map(i -> {
                    int n = Math.min(amount / coins[i], holding[i]);
                    amount -= coins[i] * n;
                    return n;
                }).sum();
    }
}
