import java.util.*;

// 서로 다른 옷의 조합 수
class Solution {
    
    public int solution(String[][] clothes) {
        int answer = 1;
        Map<String, Integer> map = new HashMap<>();
        for(String[] cloth: clothes){
            map.put(cloth[1], map.getOrDefault(cloth[1], 0)+1);
        }
        
        // 키의 개수 n개이면 nC1 ~ nCn -> 각각 경우의 수 모두 곱함
        for(String k: map.keySet()){
            answer *= map.get(k)+1;
        }
        return answer-1;
    }
}
