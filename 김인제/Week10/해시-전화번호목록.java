import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        HashMap<String, Integer> map = new HashMap<>();
        
        for (String str : phone_book) {
            map.put(str, 1);
        }
        
        for (String str : map.keySet()) {
            String tmp = "";
            
            for (int i = 0; i < str.length(); i++) {
                tmp += str.charAt(i);
                
                if (tmp.equals(str)) {
                    break;
                }
                
                if (map.get(tmp) != null) {
                    return false;
                }
            }
        }
        
        return true;
    }
}
