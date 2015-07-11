package Chapter2;

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
}
