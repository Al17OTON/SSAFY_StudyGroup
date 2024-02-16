// swea 1234. 비밀번호 (Java)

import java.util.Scanner;

class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=10;

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int pwLen = sc.nextInt();
            int[] pw = new int[pwLen];

            String[] pwInput = sc.next().split("");

            for(int i = 0; i < pwLen; i++) {
                pw[i] = Integer.parseInt(pwInput[i]);
            }

            int answer = 1;

            while(answer != 0) {
                answer = 0;
                for(int i = 0; i < pwLen - 1; i++) {
                    int j = i + 1;

                    while(pw[j] == -1 && j < pwLen - 1) {
                        j++;
                    }

                    if(pw[i] != -1 && pw[i] == pw[j]) {
                        pw[i] = -1;
                        pw[j] = -1;
                        answer++;
                    }
                }
            }

            System.out.printf("#%d ", test_case);

            for(int i = 0; i < pwLen; i++) {
                if(pw[i] != -1)
                    System.out.print(pw[i]);
            }

            System.out.println();
            // sc.close();
        }
    }
}