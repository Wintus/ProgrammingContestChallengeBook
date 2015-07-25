package Chapter3.Section1;

import java.util.Scanner;

/**
 * Math.NEED BUG FIX.
 * Created by Yuya on 2015/07/24.
 */
public class Sugoroku {
    private final int a, b;
    private int x, y;

    public Sugoroku(int a, int b) {
        this.a = a;
        this.b = b;
    }

    int[] exGcd(int a, int b) {
        int x = 1, y = 0, _x = 1, _y = 0, temp;
        while (b != 0) {
            int q = a / b;
            int r = a % b;

            a = b;
            b = r;

            temp = x;
            x = _x - q * x;
            _x = temp;

            temp = y;
            y = _y - q * y;
            _y = temp;
        }
        this.x = x;
        this.y = y;
        return new int[]{x, y};
    }

    void solve() {
        exGcd(a, b);
        if (x > 0)
            System.out.printf("%d 0 ", x);
        else
            System.out.printf("0 %d ", -x);
        if (y > 0)
            System.out.printf("%d 0\n", y);
        else
            System.out.printf("0 %d\n", -y);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            new Sugoroku(a, b).solve();
        }
    }
}
