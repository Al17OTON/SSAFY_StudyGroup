import java.util.*;

public class Solution {
    public int[] solution(int []arr) { // <= 1000000
        Stack<Integer> stk = new Stack<>();
        for(int i = 0; i<arr.length; i++){
            if(stk.size() == 0 || stk.peek() != arr[i]) {
                stk.push(arr[i]);
            }
        }
    
        int[] answer = new int[stk.size()];
        for(int i = stk.size()-1; i>=0; i--){
            answer[i] = stk.pop();
        }
        return answer;
    }
}
