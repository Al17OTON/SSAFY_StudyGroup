package Week11;

import java.util.*;

class Solution {
    public int solution(int[] cit) {
        Arrays.sort(cit);
        
        int h = cit[cit.length - 1], idx = cit.length - 1;
        
        for(; h >= 0; h--) {
            while(idx >= 0 && cit[idx] >= h) idx--;
            int count = cit.length - idx - 1;
            
            if(count >= h) break;
        }
        
        return h;
    }
}
