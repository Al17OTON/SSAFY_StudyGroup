import java.util.*;

class Solution {
    int N, maxWin;
    int[] winSel;
    int[][] dice;
    List<Integer> a, b;
    public int[] solution(int[][] dice) {
        maxWin = Integer.MIN_VALUE;
        this.dice = dice;
        N = dice.length;
        
        combination(0, 0, new int[N / 2], new boolean[N]);
        
        for(int i = 0; i < winSel.length; i++) winSel[i]++;
        
        return winSel;
    }
    
    void combination(int idx, int cnt, int[] sel, boolean[] v) {
        if(idx == sel.length) {
            a = new ArrayList();
            b = new ArrayList();
            //makeScore(0, 0, 0, v);
            makeScore(0, 0, true, v);
            makeScore(0, 0, false, v);
            //System.out.println(a.size() + " - " + b.size());
            //Collections.sort(a);
            Collections.sort(b);
            int winRate = 0;
            for(int i : a) {
                int res = binarySearch(i);
                if(res != Integer.MIN_VALUE) winRate += res + 1;
            }
            //System.out.println(winRate);
            if(maxWin < winRate) {
                maxWin = winRate;
                winSel = sel.clone();
            }
            
            return;
        }
        if(cnt >= N) return;
        
        for(int i = cnt; i < N; i++) {
            sel[idx] = i;
            v[i] = true;
            combination(idx + 1, i + 1, sel, v);
            v[i] = false;
        }
    }
    
    void makeScore(int idx, int score, boolean flag, boolean[] v) {
        if(idx == N) {
            if(flag) a.add(score);
            else b.add(score);
            return;
        }
        
        if(v[idx] == flag) {
            for(int i = 0; i < 6; i++) {
                makeScore(idx + 1, score + dice[idx][i], flag, v);
            }
        }
        else makeScore(idx + 1, score, flag, v);
    }
    
//     void makeScore(int idx, int A, int B, boolean[] v) {
//         if(idx == v.length) {
//             a.add(A);
//             b.add(B);
//             return;
//         }
        
//         if(v[idx]) {
//             for(int i = 0; i < 6; i++) {
//                 makeScore(idx + 1, A + dice[idx][i], B, v);
//             }
//         } else {
//             for(int j = 0; j < 6; j++) {
//                 makeScore(idx + 1, A, B + dice[idx][j], v);
//             }
//         }
//     }
    
    int binarySearch(int target) {
        int L = 0;
        int R = b.size() - 1;
        int mid = -1;
        int max = Integer.MIN_VALUE;
        while(L <= R) {
            mid = (L + R) / 2;

            if(b.get(mid) < target) {
                L = mid + 1;
                max = Math.max(max, mid);
            }
            else R = mid - 1;
        }
        
        return max;
    }
}












