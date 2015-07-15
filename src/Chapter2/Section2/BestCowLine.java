package Chapter2.Section2;

import java.io.StringWriter;
import java.util.Scanner;

/**
 * Greedy Algorithm.
 * Created by Yuya on 2015/07/12.
 */
public class BestCowLine {
    private final String string;

    public BestCowLine(String string) {
        this.string = string;
    }

    String solve() {
        int a = 0, b = string.length() - 1; // str left in [a, b]
        StringWriter writer = new StringWriter();
        while (a <= b) {
            boolean left = false;
            for (int i = 0; a + i <= b; i++) {
                if (string.charAt(a + i) < string.charAt(b - i)) {
                    left = true;
                    break;
                } else if (string.charAt(a + i) > string.charAt(b - i)) {
                    left = false;
                    break;
                }
            }
            if (left) writer.append(string.charAt(a++));
            else writer.append(string.charAt(b--));
        }
        return writer.toString();
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            scanner.nextInt();
            System.out.println(new BestCowLine(scanner.next()).solve());
        }
    }
}
