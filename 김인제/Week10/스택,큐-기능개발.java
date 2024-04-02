import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        
        for (int i = 0; i < progresses.length; i++) {
            int days = (100 - progresses[i]) / speeds[i];
            
            if ((100 - progresses[i]) % speeds[i] > 0) {
                days++;
            }
            
            deque.offer(days);
        }
        
        int last = deque.poll();
        int cnt = 1;
        ArrayList<Integer> list = new ArrayList<>();
        
        while (!deque.isEmpty()) {
            int p = deque.poll();
            
            if (p > last) {
                list.add(cnt);
                last = p;
                cnt = 1;
            } else {
                cnt++;
            }
        }
        
        list.add(cnt);
        
        int[] answer = new int[list.size()];
        
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}
