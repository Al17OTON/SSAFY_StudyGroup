import java.util.*;

class PRGR_가장많이받은선물 {
    
    /*
    선물을 주고받은 기록을 바탕으로 다음달에 누가 선물을 많이 받을지 예측
    
    - 두 사람이 선물을 주고 받은 기록이 있으면 더 많은 선물을 준 사람이 다음달에 선물을 더 받음
    - 하나도 없거나 같다면 선물 지수가 더 큰 사람이 받음, 선물지수도 같으면 다음 달에 주고받지 않음
    * 선물지수 = 이번 달까지 자신이 친구들에게 준 선물의 수 - 받은 수 == 많이 줬을수록 높음
    
    선물을 가장 많이 받을 친구는?
    
    그래프?
    사람별로 준사람, 받은사람 저장 -> 맵 이용해서 숫자로 저장
    
    */
    int N;
    
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        N = friends.length;
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i<friends.length; i++){
            map.put(friends[i], i);
        }
        
        int[][] graph = new int[N][N];
        int[] degree = new int[N];
        for(int i = 0; i<gifts.length; i++){
            String[] gift = gifts[i].split(" ");
            degree[map.get(gift[0])] += 1;
            degree[map.get(gift[1])] -= 1;
            graph[map.get(gift[0])][map.get(gift[1])] += 1;
        }
        
        for(int i = 0; i<N; i++){
            int temp = 0;
            for(int j = 0; j<N; j++){
                if(i == j) continue;
                if(graph[i][j] > graph[j][i] || (graph[i][j] == graph[j][i] && degree[i] > degree[j])){
                    temp += 1;
                }
            }
            
            answer = Math.max(answer, temp);
        }
        return answer;
    }
}
