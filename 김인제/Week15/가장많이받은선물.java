import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int N = friends.length;
        HashMap<String, Integer> nameHash = new HashMap<>();
        
        for (int i = 0; i < N; i++) {
            nameHash.put(friends[i], i);
        }
        
        int[][] giftTable = new int[N][N];
        
        for (int i = 0; i < gifts.length; i++) {
            String[] gift = gifts[i].split(" ");
            String from = gift[0];
            String to = gift[1];
            giftTable[nameHash.get(from)][nameHash.get(to)]++;
        }

        int[] giftScore = new int[N];
        
        for (int i = 0; i < N; i++) {
            int from = 0;
            int to = 0;
            
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    continue;
                }
                
                from += giftTable[i][j];
            }
            
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    continue;
                }
                
                to += giftTable[j][i];
            }
            
            giftScore[i] = from - to;
        }
        
        int[] giftCnt = new int[N];
        
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                if (i == j) {
                    continue;
                }
                
                if (giftTable[i][j] > giftTable[j][i]) {
                    giftCnt[i]++;
                } else if (giftTable[i][j] < giftTable[j][i]) {
                    giftCnt[j]++;
                } else {
                    if (giftScore[i] > giftScore[j]) {
                        giftCnt[i]++;
                    } else if (giftScore[i] < giftScore[j]) {
                        giftCnt[j]++;
                    }
                }
            }
        }
        
        int answer = 0;
        
        for (int cnt : giftCnt) {
            answer = Math.max(answer, cnt);
        }
        
        return answer;
    }
}
