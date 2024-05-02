class Solution {
    class Emoji {
        int raw, price, dis;
        void setDis(int d) {
            dis = d;
            price = (int)((float)raw - ((float)raw * ((float)d / 100f)));
        }
    }
    
    int[] discount = {10, 20, 30, 40};
    int[][] user;
    Emoji[] emti;
    int maxCnt, incomeMax;
    public int[] solution(int[][] users, int[] emoticons) {
        maxCnt = Integer.MIN_VALUE;
        incomeMax = Integer.MIN_VALUE;
        user = users;
        emti = new Emoji[emoticons.length];
        for(int i = 0; i < emoticons.length; i++) {
            emti[i] = new Emoji();
            emti[i].raw = emoticons[i];
        }
        powerSet(0);
        return new int[] {maxCnt, incomeMax};
    }
    
    void powerSet(int idx) {
        if(idx == emti.length) {
            sim();
            return;
        }
        
        for(int i = 0; i < discount.length; i++) {
            emti[idx].setDis(discount[i]);
            
            powerSet(idx + 1);
        }
    }
    
    void sim() {
        int cnt = 0;
        int[] income = new int[user.length];
        boolean[] v = new boolean[user.length];
        for(int i = 0; i < emti.length; i++) {
            for(int j = 0; j < user.length; j++) {
                if(v[j]) continue;
                if(user[j][0] <= emti[i].dis) {
                    income[j] += emti[i].price;
                    if(income[j] >= user[j][1]) {
                        v[j] = true;
                        cnt++;
                    }
                }
            }
        }
        if(cnt < maxCnt) return;
        int incomeSum = 0;
        for(int i = 0; i < income.length; i++) {
            if(!v[i]) incomeSum += income[i];
        }
        
        if(cnt == maxCnt) incomeMax = Math.max(incomeMax, incomeSum);
        else incomeMax = incomeSum;
        maxCnt = cnt;
    }
}