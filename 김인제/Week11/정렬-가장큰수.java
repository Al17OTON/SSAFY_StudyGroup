import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String[] strArr = new String[numbers.length];
        
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = numbers[i] + "";
        }
        
        Arrays.sort(strArr, (a, b) -> (b + a).compareTo(a + b));
        
        StringBuilder answer = new StringBuilder();
        
        for (int i = 0; i < strArr.length; i++) {
            answer.append(strArr[i]);
        }
        
        if (answer.charAt(0) == '0') {
            return "0";
        }
        
        return answer.toString();
    }
}
