// swea 2805. 농장물 수확하기 (Java)

import java.util.Scanner;

class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int N = sc.nextInt();
            int[][] farm = new int[N][N];
            int answer = 0;

            for(int i = 0; i < N; i++) {
                String[] tempInput = sc.next().split("");

                for(int j = 0; j < N; j++)
                    farm[i][j] = Integer.parseInt(tempInput[j]);
            }

            for(int i = 0; i < (N / 2) + 1; i++) {
                for(int j = (N / 2) - i; j < (N / 2) + i + 1; j++)
                    answer += farm[i][j];
            }

            for(int i = (N / 2) + 1; i < N; i++) {
                for(int j = (N / 2) - (N - 1 - i); j < (N / 2) + N - i; j++)
                    answer += farm[i][j];
            }

            System.out.printf("#%d %d\n", test_case, answer);
        }
    }
}