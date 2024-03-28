import java.util.*;

class Solution {
     public int[] solution(String[] operations) {
        StringTokenizer st;
        int[] answer = new int[2]; // <= 1,000,000
        
        PriorityQueue<Integer> max = new PriorityQueue<>(Comparator.reverseOrder()); // 최댓값
        PriorityQueue<Integer> min = new PriorityQueue<>(); // 최솟값
        int cnt = 0; 
        for(String op: operations){
            st = new StringTokenizer(op);
            String ord = st.nextToken();
            int n = Integer.parseInt(st.nextToken());
            
            if(ord.equals("I")){
                max.offer(n);
                min.offer(n);
                cnt += 1;
            }else{
                if(cnt == 0) continue;
                if(n == 1){
                    max.poll();
                    cnt -= 1;
                }else{
                	min.poll();
                    cnt -= 1;
                }
            }
            
            if(cnt == 0){
                max.clear();
                min.clear();
            }
        }
        
        if(cnt == 0) return new int[] {0, 0};
        return new int[] {max.poll(), min.poll()};
    }
}
