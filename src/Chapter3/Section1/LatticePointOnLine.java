package Chapter3.Section1;

import java.awt.*;
import java.util.Scanner;

/**
 * Math.
 * Created by Yuya on 2015/07/24.
 */
public class LatticePointOnLine {
    private final Point p0, p1;

    public LatticePointOnLine(Point p0, Point p1) {
        this.p0 = p0;
        this.p1 = p1;
    }

    /**
     * O(log max(a, b))
     *
     * @param a natural number.
     * @param b natural number.
     * @return gcd.
     */
    static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    int solve() {
        return Math.max(gcd(Math.abs(p0.x - p1.x), Math.abs(p0.y - p1.y)) - 1, 0);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Point point0 = new Point(scanner.nextInt(), scanner.nextInt());
            Point point1 = new Point(scanner.nextInt(), scanner.nextInt());
            LatticePointOnLine points = new LatticePointOnLine(point0, point1);
            System.out.println(points.solve());
        }
    }
}
