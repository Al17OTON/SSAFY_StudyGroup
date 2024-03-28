import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (int s : scoville) {
            pq.offer(s);
        }
        
        int answer = 0;
    
        while (pq.peek() < K) {
            answer++;
            
            if (pq.size() == 1) {
                answer = -1;
                
                break;
            }
            
            int a = pq.poll();
            int b = pq.poll();
            
            pq.offer(a + (b * 2));
        }
        
        return answer;
    }
}
