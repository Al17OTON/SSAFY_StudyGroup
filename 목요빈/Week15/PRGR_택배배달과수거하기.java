import java.util.*;
class PRGR_택배배달과수거하기 {
   
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long dis = 0;
        int deliver = 0, pickup = 0;
        // 마지막부터 탐색하는 것을 막기위해 
        int dLast = n-1; // 0이 아닌 가장 마지막 값 위치
        int pLast = n-1;
        for(int i = 0; i<n; i++){
            deliver += deliveries[i];
            pickup += pickups[i];
            if(deliveries[i] != 0) dLast = i;
            if(pickups[i] != 0) pLast = i;
        }   
        
        int idx = 0;
        while(pickup > 0 || deliver > 0){
            int temp = cap;
            if(pickup == 0) pLast = 0;
            if(deliver == 0) dLast = 0;
            dis += Math.max(dLast+1, pLast+1) * 2;
            
            for(int i = dLast; i>=0; i--){
                if(deliveries[i] == 0) continue;
                dLast = i;
                if(deliveries[i] > temp) {
                    deliveries[i] -= temp; // 남은 배달 > cap                    
                    deliver -= temp;
                    break;
                }else{ // deliveries[i] <= cap
                    temp -= deliveries[i];
                    deliver -= deliveries[i];
                    deliveries[i] = 0;
                }
            }

            temp = cap; // 실을 수 있는 택배 개수
            for(int i = pLast; i>=0; i--){
                if(pickups[i] == 0) continue;
                pLast = i;
                if(pickups[i] > temp){
                    pickups[i] -= temp;
                    pickup -= temp;
                    break;
                }else{ // pickups[i] <= temp
                    temp -= pickups[i];
                    pickup -= pickups[i];
                    pickups[i] = 0;
                }
            }            
        }
        
        return dis;
    }
}

 /*
n개의 집에 택배 배달 -> 빈 재활용 택배 상자 수거
트럭에 최대 cap개 실을 수 있음
각 집마다 배달할 택배 상자와 빈 재활용 택배 상자 개수가 주어짐
트럭 하나로 모든 배달과 수거를 마치고 창고까지 돌아올 최소 이동거리를 구해라.
* 각 집에 배달 및 수거할 때, 원하는 개수만큼 택배를 배달 및 수거할 수 있음 ㄷㄷ

그리디? 수가 너무 커서 완탐은 아닐듯

가는 길에 배달하고, 오는 길에 수거
max(남은 배달, cap)만큼 가지고 나가서 최대한 챙겨오기
1. 택배 챙기기: cap만큼 챙기기
2. deliveries 뒤부터 탐색하며 truck이 0이 될 때까지 -= 1 해주기 
    ==> 배달이 끝나면 트럭에는 무조건 0개가 남아있어야 함
3. pickups 뒤부터 탐색하며 cap을 가득 채울 때까지 -= 1 해주기
 -> 2, 3번의 최대값을 거리에 더해주기

일단 무조건 간만큼 돌아와야 함
*/
