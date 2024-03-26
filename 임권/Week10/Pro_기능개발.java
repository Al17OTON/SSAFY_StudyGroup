package Week10;

import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = new int[progresses.length];
        
        int idx = 0, answerIdx = 0;
        while(idx != progresses.length) {
            work(progresses, speeds);
            if(progresses[idx] >= 100) {
                int count = 0, tmp = idx;
                while(tmp < progresses.length && progresses[tmp] >= 100) {
                    tmp++;
                    count++;
                }
                answer[answerIdx++] = count;
                idx = tmp;
            }
        }
        
        return Arrays.copyOf(answer, answerIdx);
    }
    
    
    void work(int[] p, int[] s) {
        for(int i = 0; i < p.length; i++) {
            p[i] += s[i];
        }
    }
    
}