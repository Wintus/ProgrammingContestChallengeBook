package Chapter1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Problem of chapter 1
 * Created by Wintus on 2015/07/10.
 */
public class Triangle {
    int[] a;

    public Triangle(int[] a) {
        this.a = a;
    }

    void solve() {
        Arrays.sort(a);
        int max = 0;
        for (int i = 2; i < a.length; i++) {
            if (a[i] < a[i - 1] + a[i - 2]) {
                max = Math.max(max, a[i] + a[i - 1] + a[i - 2]);
            }
        }
        System.out.println(max);
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in))
        ) {
            String line = reader.readLine();
            int n = Integer.parseInt(line);
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(reader.readLine());
            }
            Triangle triangle = new Triangle(a);
            triangle.solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
