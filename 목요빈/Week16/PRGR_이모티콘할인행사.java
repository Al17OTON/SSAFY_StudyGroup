import java.util.*;

class Solution {

    PriorityQueue<int[]> pq;
    int[] ratio;
    int N;
    
    /*
        이모티콘 할인율은 10, 20, 30, 40 중 하나이다!
    */
    public int[] solution(int[][] users, int[] emoticons) {
        // users: [비율, 가격] <= 100
        // emoticons: 이모티콘 가격 <= 7
        N = emoticons.length;
        
        pq = new PriorityQueue<>((int[] c1, int[] c2) -> {
            if(c2[0] == c1[0]) return c2[1] - c1[1]; // 매출액 내림차순
            return c2[0] - c1[0]; // 서비스 가입자 내림차순
        });
        
        ratio = new int[N];
        discount(0, users, emoticons);
        
        return pq.poll();
    }
    
    public void calc(int[][] users, int[] emoticons){
        int sum = 0;
        int service = 0;
        for(int i = 0; i<users.length; i++){
            int temp = 0;
            for(int j = 0; j<N; j++){ // 이모티콘
                int emoticon = emoticons[j];
                if(users[i][0] > ratio[j]) continue;
                temp += emoticon - emoticon * ratio[j] / 100;
            }
            if(temp >= users[i][1]) service += 1;
            else sum += temp;
            pq.add(new int[]{service, sum});
        }
    }
    
    public void discount(int n, int[][] users, int[] emoticons){
        if(n == N){
            calc(users, emoticons);
            return;
        }
        
        for(int i = 1; i<= 4; i++){
            ratio[n] = i*10;
            discount(n+1, users, emoticons);
        }
    }
}
