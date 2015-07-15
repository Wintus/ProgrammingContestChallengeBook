package Chapter2.Section4;

/**
 * Using Union-Find Tree.
 * Type 1: x and y is the same type.
 * Type 2: x eats y.
 * Created by Yuya on 2015/07/14.
 */
public class FoodChain {
    int N;
    int[][] information; // {{type, x, y}}

    public FoodChain(int n, int[][] information) {
        N = n;
        this.information = information;
    }
}
