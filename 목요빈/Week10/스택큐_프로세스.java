import java.util.*;

class Solution {
    public int solution(int[] p, int location) {
        int cnt = 1;
        int size = p.length;
        int[] order = p.clone();
        Arrays.sort(order); // 오름차순 정렬
        int ordId = size-1;
        int idx = 0;
        
        L: while(true){
            for(int i = idx; i<idx+size; i++){
                
                if(p[i] == order[ordId]){
                    if(i == location) break L;
                    idx = (i + 1) % size;
                    ordId -= 1;
                    p[i] = 0;
                    break;
                }
            }
            cnt += 1;   
        }
        return cnt;
    }
}
