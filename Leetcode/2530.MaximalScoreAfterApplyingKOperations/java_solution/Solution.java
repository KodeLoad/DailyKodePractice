package java_solution;
import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    public long maxKelements(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(
            Collections.reverseOrder()
        );

        for (int n : nums) {
            queue.offer(n);
        }

        long score = 0;
        for (int i = 0; i < k; i++) {
            // at max k ops
            int curScore = queue.poll();
            score += curScore;
            curScore = (int) Math.ceil(curScore * 1.0/3.0);

            queue.offer(curScore);
        }

        return score;
    }
}
