import java.util.*;

class Solution {
    
    // 작업요청~종료까지의 시간 평균이 가장 짧은 경우를 구해라.
    // 작업이 없을 땐 먼저 요청이 들어온 작업부터 처리
    
    // 작업을 할 수 있는 놈들 중 작업 시간이 짧은 순서
    
    // 요청~종료 시간 = 작업 시간 + 대기 시간 - 요청 시간
    static class P{
        int s, t;
        P(int s, int t){
            this.s = s;
            this.t = t;
        }
        @Override
        public String toString(){
            return "start: " + s + ", time: " + t;
        }
    }
    public int solution(int[][] jobs) {
        int answer = 0;
        
        PriorityQueue<P> pq = new PriorityQueue<>((P a1, P a2) -> {
            if(a1.s == a2.s) return a1.t - a2.t;
            return a1.s - a2.s;
        });
        
        for(int i = 0; i<jobs.length; i++){
            pq.add(new P(jobs[i][0], jobs[i][1]));
        }
        
        int time = 0;
        int sum = 0;
        while(!pq.isEmpty()){
            List<P> temp = new ArrayList<>();
            while(!pq.isEmpty() && pq.peek().s <= time){ // 작업의 시작시간 <= 현재 시간 -> 실행 가능한 작업
                temp.add(pq.poll());
            }
            
            P t;
            if(temp.size() != 0){
                Collections.sort(temp, (P a1, P a2) -> {
                    return a1.t - a2.t;
                });
                t = temp.get(0);
                for(int i = 1; i<temp.size(); i++) pq.add(temp.get(i));
            }else{
                t = pq.poll();
                time += t.s-time;
            }
            
            time += t.t;
            sum += time-t.s; // 요청~종료까지의 시간 
            System.out.println(time);
        }
        System.out.println(sum);
    
        return sum / jobs.length;
    }
}
