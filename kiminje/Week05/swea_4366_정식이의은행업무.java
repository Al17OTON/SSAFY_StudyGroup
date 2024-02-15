package com.algorithm.solution.swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* SWEA 4366. 정식이의 은행업무 */
public class Problem4366 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input/swea/Input4366.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        int answer = 0;
        String binary, Ternary;
        StringBuilder strB, strT;

        for (int tc = 1; tc < T + 1; tc++) {
            binary = br.readLine();
            Ternary = br.readLine();

            for (int i = 0; i < binary.length(); i++) {
                strB = new StringBuilder(binary);

                for (int b = 0; b < 2; b++) {
                    strB.setCharAt(i, Integer.toString(b).charAt(0));

                    for (int j = 0; j < Ternary.length(); j++) {
                        strT = new StringBuilder(Ternary);

                        for (int t = 0; t < 3; t++) {
                            strT.setCharAt(j, Integer.toString(t).charAt(0));

                            if (Integer.parseInt(strB.toString(), 2) == Integer.parseInt(strT.toString(), 3)) {
                                answer = Integer.parseInt(strB.toString(), 2);
                            }
                        }
                    }
                }
            }

            System.out.println("#" + tc + " " + answer);
        }

        br.close();
    }
}
