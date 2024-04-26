package com.algorithm.solution.boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* BOJ 2470. 두 용액 */
public class Problem2470 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input/boj/Input2470.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int[] sol = new int[N];

        for (int i = 0; i < N; i++) {
            sol[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(sol);

        int start = 0;
        int end = N - 1;
        int diff = Integer.MAX_VALUE;
        int a = 0;
        int b = 0;

        while (start < end) {
            int temp = sol[start] + sol[end];

            if (temp == 0) {
                a = sol[start];
                b = sol[end];

                break;
            }

            if (diff > Math.abs(temp)) {
                diff = Math.abs(temp);
                a = sol[start];
                b = sol[end];
            }

            if (temp < 0) {
                start++;
            } else {
                end--;
            }
        }

        System.out.println(a + " " + b);

        br.close();
    }
}
