import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        int tmp = -1;
        ArrayList<Integer> list = new ArrayList<>();
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == tmp) {
                continue;
            }
            
            list.add(arr[i]);
            tmp = arr[i];
        }
        
        int[] answer = new int[list.size()];
        
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
}
