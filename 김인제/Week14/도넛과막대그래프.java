import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = {0, 0, 0, 0};
        int[] outCnt = new int[1000001];
        int[] inCnt = new int[1000001];
        boolean[] numCheck = new boolean[1000001];
        
        for (int[] edge : edges) {
            outCnt[edge[0]] += 1;
            inCnt[edge[1]] += 1;
            numCheck[edge[0]] = true;
            numCheck[edge[1]] = true;
        }
        
        for (int i = 1; i <= 1000000; i++) {
            if (!numCheck[i]) {
                continue;
            } else if (outCnt[i] == 0) {
                answer[2]++;
            } else if (outCnt[i] == 1) {
                continue;
            } else if (outCnt[i] == 2 && inCnt[i] > 0) {
                answer[3]++;
            } else {
                answer[0] = i;
            }
        }
        
        answer[1] = outCnt[answer[0]] - answer[2] - answer[3];
        
        return answer;
    }
}
