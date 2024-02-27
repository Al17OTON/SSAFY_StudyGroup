package com.algorithm.solution.swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/* SWEA 7206. 숫자 게임 */
public class Problem7206 {
    static String str;
    static int answer;
    static ArrayList<int[]>[] subsets = new ArrayList[6];
    static int[] visited;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input/swea/Input7206.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i < 6; i++) {
            subsets[i] = new ArrayList<>();
            getSubset(1, i, new ArrayList<>());
        }

        for (int tc = 1; tc < T + 1; tc++) {
            str = br.readLine();

            answer = 0;
            visited = new int[100000];
            cut(str, 0);

            System.out.println("#" + tc + " " + answer);
        }

        br.close();
    }

    private static void getSubset(int start, int idx, ArrayList<Integer> list) {
        if (!list.isEmpty()) {
            int[] temp = new int[list.size()];

            for (int i = 0; i < list.size(); i++) {
                temp[i] = list.get(i);
            }

            subsets[idx].add(temp);
        }

        for (int i = start; i < idx; i++) {
            list.add(i);
            getSubset(i + 1, idx, list);
            list.remove(list.size() - 1);
        }
    }

    private static void cut(String str, int cnt) {
        if (str.length() == 1) {
            answer = Math.max(answer, cnt);

            return;
        }

        for (int i = 0; i < subsets[str.length()].size(); i++) {
            ArrayList<String> temp = new ArrayList<>();
            int[] set = subsets[str.length()].get(i);

            temp.add(str.substring(0, set[0]));

            for (int j = 0; j < set.length - 1; j++) {
                temp.add(str.substring(set[j], set[j + 1]));
            }

            temp.add(str.substring(set[set.length - 1], str.length()));

            int n = 1;

            for (String s : temp) {
                n *= Integer.parseInt(s);
            }

            if (visited[n] == 0) visited[n] = cnt;
            else if (visited[n] > cnt) continue;

            cut(n + "", cnt + 1);
        }
    }
}
