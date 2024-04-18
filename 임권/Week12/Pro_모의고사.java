package Week12;

class Solution {
    int[] answers;
    int[] second = {2, 1, 2, 3, 2, 4, 2, 5};
    int[] third = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
    public int[] solution(int[] answers) {
        this.answers = answers;
        
        int[] res = dfs(0, new int[3]);
        
        int max = 0, cnt = 0;
        for(int s : res) {
            if(s > max) {
                cnt = 1;
                max = s;  
            } else if(s == max) cnt++;
        }
        int[] answer = new int[cnt];
        cnt = 0;
        for(int i = 0; i < res.length; i++) {
            if(max == res[i]) answer[cnt++] = i + 1;
        }
        
        return answer;
    }
    
    int[] dfs(int idx, int[] score) {
        if(idx == answers.length) {
            return score;
        }
        
        if((idx % 5 + 1) == answers[idx]) score[0]++;
        if(second[idx % 8] == answers[idx]) score[1]++;
        if(third[idx % 10] == answers[idx]) score[2]++;
        
        return dfs(idx + 1, score);
    }
}
