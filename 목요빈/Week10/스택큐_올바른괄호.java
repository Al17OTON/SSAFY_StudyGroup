import java.util.*;

class Solution {
    boolean solution(String s) { // s <= 100,000
        Stack<Character> stk = new Stack<>();
        
        for(int i = 0; i<s.length(); i++){
            char cur = s.charAt(i);
            if(cur == '(') {
                stk.add(cur);
            }else{
                if(stk.isEmpty() || stk.peek() != '(') return false;
                stk.pop();
            }
        }
        
        if(!stk.isEmpty()) return false;
        return true;
    }
}
