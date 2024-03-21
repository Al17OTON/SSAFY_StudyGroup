import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> map = new HashMap<>();
        
        for (String str : completion) {
            if (map.get(str) == null) {
                map.put(str, 1);
            } else {
                map.put(str, map.get(str) + 1);
            }
        }
        
        String answer = "";
        
        for (String str : participant) {
            if (map.get(str) == null || map.get(str) == 0) {
                answer += str;
            } else {
                map.put(str, map.get(str) - 1);
            }
        }

        return answer;
    }
}
