package Chapter2.Section4;

import java.util.PriorityQueue;

/**
 * Using Huffman Coding.
 * Created by Yuya on 2015/07/14.
 */
public class FenceRepair {
    private int[] boards;

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
}
