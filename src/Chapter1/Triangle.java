package Chapter1;

import java.util.Arrays;

/**
 * Problem of chapter 1
 * Created by Wintus on 2015/07/10.
 */
public class Triangle {
    int n;
    int[] a;

    public Triangle(int n, int[] a) {
        this.n = n;
        this.a = new int[n];
        this.a = a;
    }

    void solve() {
        Arrays.sort(a);
        int max = 0;
        for (int i = 2; i < n; i++) {
            if (a[i] < a[i - 1] + a[i - 2]) {
                max = Math.max(max, a[i] + a[i - 1] + a[i - 2]);
            }
        }
        System.out.println(max);
    }

    public static void main(String[] args) {

    }
}
