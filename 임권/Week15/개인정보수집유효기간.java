import java.util.*;

class Solution {
    int[] period;
    int Y, M, D;
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> list = new ArrayList();
        period = new int[26];
        String[] cur = today.split("\\.");
        Y = Integer.parseInt(cur[0]);
        M = Integer.parseInt(cur[1]);
        D = Integer.parseInt(cur[2]);
        
        for(String term : terms) {
            String[] a = term.split(" ");
            period[a[0].charAt(0) - 'A'] = Integer.parseInt(a[1]);
        }
        
        for(int i = 0; i < privacies.length; i++) {
            String[] privac = privacies[i].split(" ");
            char t = privac[1].charAt(0);
            String[] date = privac[0].split("\\.");
            int year = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]);
            int day = Integer.parseInt(date[2]);
            int[] res = calc(year, month, period[t - 'A']);
            
            if(isExpired(res[0], res[1], day)) {
                list.add(i + 1);
            }
        }
        
        int[] answer = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
    
    boolean isExpired(int y, int m, int d) {
        if(Y > y) return true;
        else if(Y == y && M > m) return true;
        else if(Y == y && M == m && D >= d) return true;
        return false;
    }
    
    int[] calc(int y, int m, int p) {
        y += p / 12;
        m += p % 12;
        if(m > 12) {
            y++;
            m -= 12;
        }
        return new int[] {y, m};
    }
    
}