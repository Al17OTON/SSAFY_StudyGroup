import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<int[]> stack = new Stack<>();
        
        for (int i = 0; i < prices.length; i++) {
            while (!stack.isEmpty() && stack.peek()[0] > prices[i]) {
                int[] s = stack.pop();
                answer[s[1]] = i - s[1];
            }
            
            stack.add(new int[] {prices[i], i});
        }
        
        while (!stack.isEmpty()) {
            int[] s = stack.pop();
            answer[s[1]] = (prices.length - 1) - s[1];
        } 
        
        return answer;
    }
}
