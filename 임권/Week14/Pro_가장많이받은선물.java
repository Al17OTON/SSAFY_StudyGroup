import java.util.*;

class Solution {
    
    class Friend {
        int[] log;
        int in, out;
        
        public Friend(int size) {
            log = new int[size];
            in = 0;
            out = 0;
        }
        public void getGift() {
            in++;
        }
        public void giveGift(int to) {
            out++;
            log[to]++;
        }
        public int getSub() {
            return out - in;
        }
    }
    
    HashMap<String, Integer> hash;
    Friend[] f;
    int[] thisMonth;
    public int solution(String[] friends, String[] gifts) {
        hash = new HashMap();
        f = new Friend[friends.length];
        thisMonth = new int[f.length];
        
        for(int i = 0; i < friends.length; i++) {
            hash.put(friends[i], i);
            f[i] = new Friend(friends.length);
        }        
        
        StringTokenizer st;
        for(String gift : gifts) {
            st = new StringTokenizer(gift);
            
            int aIdx = hash.get(st.nextToken());
            int bIdx = hash.get(st.nextToken());
            
            f[aIdx].giveGift(bIdx);
            f[bIdx].getGift();
        }
        
        for(int i = 0; i < f.length; i++) {
            for(int j = i + 1; j < f.length; j++) {
                
                if(f[i].log[j] == f[j].log[i]) {
                    
                    int a = f[i].getSub();
                    int b = f[j].getSub();
                    
                    //System.out.println("i = " + i + " - " + a + " , j = " + j + " - " + b);
                    
                    if(a > b) thisMonth[i]++;
                    else if(b > a) thisMonth[j]++;
                    
                } else if(f[i].log[j] != 0 || f[j].log[i] != 0) {
                    int maxIdx = getMaxIdx(i, j);
                    thisMonth[maxIdx]++;
                    
                     //System.out.println("i = " + i + " , j = " + j + " ----- " + maxIdx);
                }
            }
        }
        
        int max = 0;
        for(int i = 0; i < f.length; i++) {
            //System.out.println(thisMonth[i]);
            max = Math.max(max, thisMonth[i]);
        }
        return max;
    }
    
    // 더 많은 선물을 준 사람 찾기
    int getMaxIdx(int aIdx, int bIdx) {
        return f[aIdx].log[bIdx] > f[bIdx].log[aIdx] ? aIdx : bIdx;
    }
}