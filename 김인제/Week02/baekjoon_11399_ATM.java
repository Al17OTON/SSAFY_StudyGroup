import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] P = new int[N];

        for (int i = 0; i < N; i++) {
            P[i] = sc.nextInt();
        }

        Arrays.sort(P);

        int[] dp = new int[N];
        dp[0] = P[0];
        int answer = dp[0];
        
        for (int i = 1; i < N; i++) {
			dp[i] = dp[i - 1] + P[i];
            answer += dp[i];
		}
        
        System.out.println(answer);

        // sc.close();
    }
}