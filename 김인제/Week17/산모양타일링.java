import java.util.*;

class Solution {
    static final int MOD = 10007;
    
    public int solution(int n, int[] tops) {
        int[][] dp = new int[n + 1][2];
        dp[0][0] = 1;
        dp[0][1] = 0;
        
        for (int i = 1; i <= n; i++) {
            if (tops[i - 1] == 0) {
                dp[i][0] = ((dp[i - 1][0] * 2) + dp[i - 1][1]) % MOD;
                dp[i][1] = (dp[i - 1][0] + dp[i - 1][1]) % MOD;
            } else {
                dp[i][0] = ((dp[i - 1][0] * 3) + (dp[i - 1][1] * 2)) % MOD;
                dp[i][1] = (dp[i - 1][0] + dp[i - 1][1]) % MOD;
            }
        }
        
        int answer = (dp[n][0] + dp[n][1]) % MOD;
        return answer;
    }
}
