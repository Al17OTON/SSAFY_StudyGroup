package com.algorithm.solution.swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* SWEA 5672. [Professional] 올해의 조련사 */
public class Problem5672 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input/swea/Input5672.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc < T + 1; tc++) {
            int N = Integer.parseInt(br.readLine());
            char[] first = new char[N];

            StringBuilder answer = new StringBuilder();

            for (int i = 0; i < N; i++) {
                first[i] = br.readLine().charAt(0);
            }

            int start = 0, end = N - 1, ts, te;

            while (start <= end) {
                if (start == end) {
                    answer.append(first[start++]);

                    break;
                }

                ts = start;
                te = end;

                if (first[start] < first[end]) answer.append(first[start++]);
                else if (first[start] > first[end]) answer.append(first[end--]);
                else {
                    while (ts <= te) {
                        if (++ts < --te) {
                            if (first[ts] < first[te]) {
                                answer.append(first[start++]);

                                break;
                            }
                            else if (first[ts] > first[te]) {
                                answer.append(first[end--]);

                                break;
                            }
                            else {
                                continue;
                            }
                        } else {
                            answer.append(first[start++]);

                            break;
                        }
                    }
                }
            }

            System.out.print("#" + tc + " ");
            System.out.println(answer);
        }

        br.close();
    }
}
