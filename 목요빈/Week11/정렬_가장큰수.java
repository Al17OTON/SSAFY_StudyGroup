import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        String[] nums = new String[numbers.length];
        
        for(int i = 0; i<numbers.length; i++){
            nums[i] = String.valueOf(numbers[i]);
        }
        
        Arrays.sort(nums, (n1, n2) -> (n2 + n1).compareTo(n1 + n2));
        
        if(nums[0].equals("0")) return "0";
        return String.join("", nums);
    }
}
