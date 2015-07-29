package Chapter3.Section3;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Without Physical Simulation. Regards all balls passing each other.
 * Created by Yuya on 2015/07/29.
 */
public class PhysicsExperiment {
    private final int H, R, T;
    private final double[] ys;
    private static final double g = 10.0;

    public PhysicsExperiment(int n, int h, int r, int t) {
        H = h;
        R = r;
        T = t;
        ys = new double[n];
    }

    private double position(int T) {
        if (T < 0) return H;
        double t = Math.sqrt(2 * H / g);
        int k = (int) (T / t);
        double dt;
        if (k % 2 == 0) // returned to the top
            dt = T - k * t;
        else // bounced at the bottom
            dt = k * t + t - T;
        return H - g * dt * dt / 2;
    }

    String solve() {
        Arrays.setAll(ys, i -> position(T - i));
        Arrays.sort(ys);
        for (int i = 0; i < ys.length; i++) ys[i] += 2 * R * i / 100.0;
        return Arrays.stream(ys)
                     .mapToObj(y -> String.format("%.2f", y))
                     .collect(Collectors.joining(" "));
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int h = scanner.nextInt();
            int r = scanner.nextInt();
            int t = scanner.nextInt();
            System.out.println(new PhysicsExperiment(n, h, r, t).solve());
        }
    }
}
