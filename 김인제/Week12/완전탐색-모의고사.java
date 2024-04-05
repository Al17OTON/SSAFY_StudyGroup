import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[][] pattern = {{1, 2, 3, 4, 5}, {2, 1, 2, 3, 2, 4, 2, 5}, {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}};
        int[] pCnt = {5, 8, 10};
        int[] index = new int[3];
        int[] score = new int[3];

        for (int i = 0; i < answers.length; i++) {
            for (int j = 0; j < 3; j++) {
                if (answers[i] == pattern[j][index[j]++]) {
                    score[j]++;
                }
                
                index[j] %= pCnt[j];
            }
        }
        
        int max = Math.max(Math.max(score[0], score[1]), score[2]);
        ArrayList<Integer> list = new ArrayList<>();
        
        for (int i = 0; i < 3; i++) {
            if (score[i] == max) {
                list.add(i + 1);
            }
        }
        
        int[] answer = new int[list.size()];
        
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}
