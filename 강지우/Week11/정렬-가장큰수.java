import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String[] nums = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            nums[i] = String.valueOf(numbers[i]);
        }

//      두개의 문자열을 합한뒤 사전순으로 정렬
//      ex) 610 106
        Arrays.sort(nums, (a, b) -> (b + a).compareTo(a + b));
        // System.out.println(Arrays.toString(nums));

        StringBuilder sb = new StringBuilder();
        for (String num : nums) {
            sb.append(num);
        }

//      예외 -> 000
        if (sb.charAt(0) == '0') {
            return "0";
        }

        return sb.toString();
    }
}
