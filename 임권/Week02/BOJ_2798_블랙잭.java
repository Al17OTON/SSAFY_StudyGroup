package Week02;

import java.util.Scanner;

public class BOJ_2798_블랙잭 {
    static int[] arr;
    static int N, M;

    static int dfs(int sum, int idx, int count) {
        if(sum > M) return 0;
        if(count == 3) {
            return sum;
        }
        if(idx == N) return 0;

        return Math.max(dfs(sum + arr[idx], idx + 1, count + 1), dfs(sum, idx + 1, count));

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        arr = new int[N];

        for(int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();            
        }

        System.out.println(dfs(0, 0, 0));

    }

}