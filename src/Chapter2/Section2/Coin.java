package Chapter2.Section2;

/**
 * Ê√ó~ñ@ - greedy algorithm
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
}
