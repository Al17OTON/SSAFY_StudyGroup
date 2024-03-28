package Week11;

import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] res = new int[commands.length];
        int idx = 0;
        for(int[] com : commands) {
            int[] copy = Arrays.copyOfRange(array, com[0] - 1, com[1]);
            Arrays.sort(copy);
            res[idx++] = copy[com[2] - 1];
        }
        
        return res;
    }
}
