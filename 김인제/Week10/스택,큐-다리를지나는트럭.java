import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        
        for (int i = 0; i < bridge_length; i++) {
            dq.offer(0);
        }
        
        int w = 0;
        int idx = 0;
        int answer = 0;
        
        while (idx < truck_weights.length) {
            answer++;
            w -= dq.poll();
            
            if (w + truck_weights[idx] <= weight) {
                w += truck_weights[idx];
                dq.offer(truck_weights[idx]);
                idx++;
            } else {
                dq.offer(0);
            }
        }        
        
        return answer + dq.size();
    }
}
