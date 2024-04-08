import java.util.*;

class Solution {
    
    // 논문 n편 중 h번 이상 인용된 논문이 h편 이상
    // h의 최댓값이 과학자의 H-index
    public int solution(int[] c) {
        int a = 0;
        int n = c.length;
        Arrays.sort(c); // n <= 1000

        for(int i = n-1; i >= 0; i--){
            if(c[i] > n-i){
                a = n-i;
                continue;
            }
            a = Math.max(a, c[i]);
        }
        
        return a;
    }
}
