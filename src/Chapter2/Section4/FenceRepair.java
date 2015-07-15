package Chapter2.Section4;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Using Huffman Coding.
 * Created by Yuya on 2015/07/14.
 */
public class FenceRepair {
    private final int[] boards;

    public FenceRepair(int[] boards) {
        this.boards = boards;
    }

    int solve() {
        int answer = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int board : boards) queue.add(board);
        while (queue.size() > 1) {
            int b0, b1;
            b0 = queue.poll();
            b1 = queue.poll();
            int b = b0 + b1;
            answer += b;
            queue.add(b);
        }
        return answer;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int[] boards = new int[n];
            Arrays.setAll(boards, x -> scanner.nextInt());
            System.out.println(new FenceRepair(boards).solve());
        }
    }
}
