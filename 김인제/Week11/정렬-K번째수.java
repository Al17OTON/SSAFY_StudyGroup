import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for (int i = 0; i < commands.length; i++) {
            int[] comm = commands[i];
            int[] arr = Arrays.copyOfRange(array, comm[0] - 1, comm[1]);
            
            Arrays.sort(arr);
            
            answer[i] = arr[comm[2] - 1];
        }
        
        return answer;
    }
}
