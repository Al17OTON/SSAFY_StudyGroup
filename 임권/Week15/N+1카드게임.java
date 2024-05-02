class Solution {
    boolean[] hand, memo;
    int N;
    public int solution(int coin, int[] cards) {
        N = cards.length;
        hand = new boolean[N + 1];
        memo = new boolean[N + 1];
        int pass = 0, onePass = 0, twoPass = 0;
        for(int i = 0; i < N / 3; i++) {
            hand[cards[i]] = true;
            if(hand[N + 1 - cards[i]]) pass++;
        }
        
        int round = 1;
        int idx = N / 3;
        while(true) {
            if(idx == N) break;
            int a = cards[idx++];
            int b = cards[idx++];
            
            memo[a] = true;
            if(memo[N + 1 - a]) twoPass++;
            else if(hand[N + 1 - a]) onePass++;
            memo[b] = true;
            if(memo[N + 1 - b]) twoPass++;
            else if(hand[N + 1 - b]) onePass++;
            
            if(pass > 0) {
                pass--;
                
            } else if(onePass > 0 && coin > 0) {
                onePass--;
                coin--;
            } else if(twoPass > 0 && coin > 1) {
                twoPass--;
                coin -= 2;
            } else break;
            
            round++;
            
        }
        return round;
        
    }
}