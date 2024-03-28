import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        int last = 0;
        PriorityQueue<int[]> pqs = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> pqe = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        
        for (int[] job : jobs) {
            pqs.offer(job);
        }
       
        while (!pqs.isEmpty() || ! pqe.isEmpty()) {
            while (!pqs.isEmpty() && pqs.peek()[0] <= last) {
                pqe.offer(pqs.poll());
            }

            if (pqe.isEmpty()) {
                last = pqs.peek()[0];
            } else {
                int[] pe = pqe.poll();
                answer += last + pe[1] - pe[0];
                last += pe[1];
            }
        }

        return answer / jobs.length;
    }
}
