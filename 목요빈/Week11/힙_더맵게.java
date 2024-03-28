import java.util.*;

class Solution {
    
    /*
    모든 음식의 스코빌 지수를 K 이상으로 만들고싶음
    스코빌 지수가 낮은 음식 2개를 섞어 스코빌 지수 높임
    */
    public int solution(int[] scoville, int K) {
        // scoville <= 1,000,000 
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int low = 0;
        int cnt = 0;
        for(int s: scoville){
            if(s < K) low += 1;
            pq.add(s);
        }
        
        while(low > 0 && pq.size() >= 2){
            int low1 = pq.poll();
            int low2 = pq.poll();
            int mix = low1 + low2 * 2;
            pq.add(mix);
            low -= 1;
            
            if(mix >= K && low2 < K){
                low -= 1;
            }
            cnt += 1;
        }
        
        if(pq.poll() < K) cnt = -1;
        
        return cnt;
    }
}
