class Solution {
    public int[] solution(int[] p) {
        int[] answer = new int[p.length];
        // 가격이 떨어지지 않은 기간은 몇 초인지 구해라.
        for(int i = 0; i<p.length-1; i++){
            int cnt = 0;
            for(int j = i+1; j<p.length; j++){
                cnt += 1; // 다음 가격이 현재보다 낮든 높든 1초는 지나가므로 일단 +1
                if(p[j] < p[i]) break;
            }
            answer[i] = cnt;
        }
        
        return answer;
    }
}
