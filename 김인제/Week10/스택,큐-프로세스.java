import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        
        for (int i = 0; i < priorities.length; i++) {
            dq.offer(new int[] {i, priorities[i]});
            pq.offer(priorities[i]);
        }
        
        int answer = 0;
        
        L : while (!pq.isEmpty()) {
            int pp = pq.poll();
            
            while (true) {
                int[] dp = dq.poll();
                
                if (dp[1] != pp) {
                    dq.offer(dp);
                } else {
                    answer++;
                    
                    if (dp[0] == location) {
                        break L;
                    }
                    
                    break;
                }
            }
        }
        
        return answer;
    }
}
