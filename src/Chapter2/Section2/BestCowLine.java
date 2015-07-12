package Chapter2.Section2;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Greedy Algorithm.
 * Created by Yuya on 2015/07/12.
 */
public class BestCowLine {
    char[] chars;

    public BestCowLine(String string) {
        chars = string.toCharArray();
    }

    String solve() {
        Deque<Character> deque = new LinkedList<>();
        for (char c : chars) deque.add(c);
        StringWriter writer = new StringWriter();
        while (deque.size() > 0) {
            if (deque.peekFirst() < deque.peekLast())
                writer.append(deque.pollFirst());
            else
                writer.append(deque.pollFirst());
        }
        return writer.toString();
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            scanner.nextInt();
            System.out.println(new BestCowLine(scanner.next()));
        }
    }
}
