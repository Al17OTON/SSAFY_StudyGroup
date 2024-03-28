package Week11;

import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue();
        for(int a : scoville) pq.offer(a);
        
        int count = 0;
        while(pq.size() > 1 && pq.peek() < K) {
            int a = pq.poll();
            int b = pq.poll();
            pq.offer(a + b * 2);
            count++;
        }
        
        if(pq.peek() < K) return -1;
        return count;
    }
}