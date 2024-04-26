import java.util.*;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int last = n - 1;
        
        L : while (last >= 0) {
            for (int i = last; i >= 0; i--) {
                if (deliveries[i] > 0 || pickups[i] > 0) {
                    last = i;
                    break;
                }
                
                if (i == 0) {
                    last = -1;
                    break L;
                }
            }
            
            int idx = last;
            int rem = cap;
            while (idx >= 0 && rem > 0) {
                if (deliveries[idx] > 0) {
                    deliveries[idx]--;
                    rem--;
                } else {
                    idx--;
                }
            }
            idx = last;
            rem = cap;
            while (idx >= 0 && rem > 0) {
                if (pickups[idx] > 0) {
                    pickups[idx]--;
                    rem--;
                } else {
                    idx--;
                }
            }
            
            answer += (last + 1) * 2;
        
        }
        
        return answer;
    }
}
