import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        int maxR = 0;
        int maxC = 0;
        
        for (int i = 0; i < sizes.length; i++) {
            maxR = Math.max(maxR, Math.max(sizes[i][0], sizes[i][1]));
            maxC = Math.max(maxC, Math.min(sizes[i][0], sizes[i][1]));
        }
        
        return maxR * maxC;
    }
}
