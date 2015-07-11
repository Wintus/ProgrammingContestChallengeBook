package Chapter2;

import java.util.Scanner;

/**
 * Chapter 2.
 * Created by Yuya on 2015/07/11.
 */
class SubsetSum {
    final int[] a;
    final int sum;

    public SubsetSum(int[] a, int sum) {
        this.a = a;
        this.sum = sum;
    }

    boolean solve() {
        return dfs(0, 0);
    }

    private boolean dfs(int n, int sum) {
        if (n == a.length) return sum == this.sum;
        if (dfs(n + 1, sum)) return true;
        else if (dfs(n + 1, sum + a[n])) return true;
        return false;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = scanner.nextInt();
            int sum = scanner.nextInt();
            System.out.println(new SubsetSum(a, sum).solve() ? "Yes" : "No");
        }
    }
}
