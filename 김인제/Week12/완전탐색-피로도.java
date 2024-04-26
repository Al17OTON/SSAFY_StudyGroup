import java.util.*;

class Solution {
    static int k, answer;
    static int[] arr, per;
    static int[][] dungeons;
    static boolean[] visited;
    
    public int solution(int k, int[][] dungeons) {
        this.k = k;
        arr = new int[dungeons.length];
        per = new int[dungeons.length];
        this.dungeons = dungeons;
        visited = new boolean[dungeons.length];
        
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        
        permu(0);
         
        return answer;
    }
    
    static void permu(int depth) {
        if (depth == arr.length) {
            go();
            
            return;
        }
        
        for (int i = 0; i < arr.length; i++) {
            if (visited[i]) {
                continue;
            }
            
            visited[i] = true;
            per[depth] = arr[i];
            permu(depth + 1);
            visited[i] = false;
        }
    }
    
    static void go() {
        int kk = k;
        int cnt = 0;
        
        for (int i = 0; i < per.length; i++) {
            if (kk >= dungeons[per[i]][0]) {
                cnt++;
                kk -= dungeons[per[i]][1];
            }
        }
        
        answer = Math.max(answer, cnt);
    }
}
