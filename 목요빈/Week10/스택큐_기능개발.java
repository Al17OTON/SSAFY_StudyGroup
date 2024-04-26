import java.util.*;

class Solution {
    public int[] solution(int[] p, int[] s) {
        // 100이 되려면 필요한 n 구하기 -> 더하기 일괄 작업 -> 넘으면 pop(인덱스 세기) -> speed 해당 인덱스부터 앞 과정 반복
        
        // 100이 되려면 필요한 n값 큐에 넣기 -> 큐 순회
        
        Queue<Integer> que = new ArrayDeque<>();
        List<Integer> ans = new ArrayList<>();
        
        for(int i = 0; i<s.length; i++){
            int n = (100-p[i]) / s[i];
            if((100-p[i]) % s[i] != 0) n += 1;
            que.add(n);
        }
        
        int cnt = 1;
        int t = que.poll(); // 맨 앞 값
        while(!que.isEmpty()){
            if(t >= que.peek()){
                que.poll();
                cnt += 1;
            }else{
                ans.add(cnt);
                cnt = 1;
                t = que.poll();
            }
        }
        ans.add(cnt);
        
        int[] answer = new int[ans.size()];
        for(int i = 0; i<ans.size(); i++){
            answer[i] = ans.get(i);
        }
        
        return answer;
    }
}
