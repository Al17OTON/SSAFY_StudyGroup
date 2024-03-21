import java.util.*;

class Solution {
    // 어떤 번호가 다른 번호의 접두어인 경우
    
    public boolean solution(String[] p) {
        boolean answer = true;
        Arrays.sort(p);
        
        for(int i = 0; i<p.length-1; i++){
            // 앞 값이 짧으면
            if(p[i].length() < p[i+1].length()){
                if(p[i+1].contains(p[i])){
                // if(p[i].equals(p[i+1].substring(0, p[i].length()))){
                    return false;
                }
            }
        }
        return true;
    }
}
