package Week10;

import java.util.*;

class Solution {
    int[] wear = new int[31];
    int count = 0;
    public int solution(String[][] clothes) {
        int idx = 0;
        HashMap<String, Integer> hash = new HashMap();
        
        for(String[] cloth : clothes) {
            Integer v = hash.get(cloth[1]);
            if(v == null) {
                wear[idx]++;
                hash.put(cloth[1], idx++);
            }
            else {
                wear[v]++;
            }
        }
        powerSet(0, idx);
        
        return count - 1;
    }
    
    void powerSet(int idx, int limit) {
        if(idx == limit) {
            count++;
            return;
        }
        
        for(int i = 0; i <= wear[idx]; i++) {
            powerSet(idx + 1, limit);
        }
    }
}