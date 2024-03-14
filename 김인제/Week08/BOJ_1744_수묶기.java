package com.algorithm.solution.boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/* BOJ 1744. 수 묶기 */
public class Problem1744 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input/boj/Input1744.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pqPos = new PriorityQueue<>((a, b) -> b - a);
        PriorityQueue<Integer> pqNev = new PriorityQueue<>();
        boolean checkZero = false;

        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(br.readLine());

            if (input > 0) {
                pqPos.offer(input);
            } else if (input < 0) {
                pqNev.offer(input);
            } else {
                checkZero = true;
            }
        }

        int answer = 0;

        while (!pqPos.isEmpty()) {
            int n = pqPos.poll();

            if (n == 1) {
                answer += 1 + pqPos.size();

                break;
            } else {
                if (!pqPos.isEmpty()) {
                    int m = pqPos.poll();

                    if (m == 1) {
                        answer += n + m;
                    } else {
                        answer += n * m;
                    }
                } else {
                    answer += n;
                }
            }
        }

        while (!pqNev.isEmpty()) {
            int n = pqNev.poll();

            if (!pqNev.isEmpty()) {
                int m = pqNev.poll();

                answer += n * m;
            } else if (!checkZero) {
                answer += n;
            }
        }

        System.out.println(answer);

        br.close();
    }
}
