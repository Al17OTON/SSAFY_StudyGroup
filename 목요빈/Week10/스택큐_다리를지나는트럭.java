import java.util.*;

class Solution {
    // 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지
    // 다리에 최대 l개, w 이하의 무게를 견딜 수 있음
    
    class Truck{
        int w, loc;
        Truck(int w, int loc){
            this.w = w;
            this.loc = loc;
        }
    }
    
    public int solution(int len, int w, int[] truck_weights) {
        Queue<Truck> que = new ArrayDeque<>();
        int cnt = 0;
        int weight = 0;
        int i = 0;
        while(i < truck_weights.length || !que.isEmpty()){
            // 트럭 이동
            int size = que.size();
            for(int j= 0; j<size; j++){
                Truck t = que.poll();
                if(t.loc + 1 < len) { // 트럭이 아직 다리 위에 있으면
                    t.loc += 1;                            
                    que.offer(t);
                }else{ // 트럭이 다리를 다 건너면 해당 트럭 무게 빼주기
                    weight -= t.w;
                }
            }
            
            // 다리가 꽉 차있지 않으면서 대기 트럭이 있으면
            if(que.size() < len && i < truck_weights.length){ 
                if(weight + truck_weights[i] <= w){ // 다음 트럭이 올라올 수 있으면
                    que.add(new Truck(truck_weights[i], 0));
                    weight += truck_weights[i];
                    i += 1;
                }
            }
            cnt += 1;
        }
        return cnt;
    }
}
