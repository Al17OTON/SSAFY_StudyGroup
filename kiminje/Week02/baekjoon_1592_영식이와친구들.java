// baekjoon 1592 Java

import java.util.Scanner;
// import java.io.FileInputStream;

public class Main
{
    static int N, M, L;

    public static void main(String args[]) throws Exception
    {
        // System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        L = sc.nextInt() % N;

        int[] ballCount = new int[N];
        int idx = 0, answer = 0;

        while (true) {
            ballCount[idx]++;
            answer++;

            if (ballCount[idx] == M) {
                break;
            }

            idx = pass(idx, ballCount[idx] % 2);
        }

        System.out.println(answer - 1);

        sc.close();
    }

    static int pass(int idx, int dir) {
        if (dir == 0) {
            if (idx - L < 0) {
                idx = N + idx - L;
            } else {
                idx = idx - L;
            }

//			for (int i = 0; i < L; i++) {
//				if (idx - 1 < 0) {
//					idx = N;
//				}
//
//				idx--;
//			}
        } else {
            if (idx + L >= N) {
                idx = idx + L - N;
            } else {
                idx = idx + L;
            }
//			for (int i = 0; i < L; i++) {
//				if (idx + 1 == N) {
//					idx = -1;
//				}
//
//				idx++;
//			}
        }

        return idx;
    }

}