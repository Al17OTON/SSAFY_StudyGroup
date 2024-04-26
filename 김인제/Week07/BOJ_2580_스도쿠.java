package com.algorithm.solution.boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/* BOJ 2580. 스도쿠 */
public class Problem2580 {
    static boolean getanswer;
    static int[][] sudoku = new int[9][9];
    static List<int[]> blanks = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input/boj/Input2580.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int r = 0; r < sudoku.length; r++) {
            st = new StringTokenizer(br.readLine());

            for (int c = 0; c < sudoku[r].length; c++) {
                sudoku[r][c] = Integer.parseInt(st.nextToken());

                if (sudoku[r][c] == 0) blanks.add(new int[] {r, c});
            }
        }

        getSudoku(0);

        for (int r = 0; r < sudoku.length; r++) {
            for (int c = 0; c < sudoku[r].length; c++) {
                System.out.print(sudoku[r][c] + " ");
            }

            System.out.println();
        }

        br.close();
    }

    private static void getSudoku(int brankIdx) {
        if (brankIdx == blanks.size()) {
            getanswer = true;
            return;
        }

        int r = blanks.get(brankIdx)[0];
        int c = blanks.get(brankIdx)[1];

        for (int i = 1; i < 10; i++) {
            sudoku[r][c] = i;

            if (checkSudoku(r, c)) getSudoku(brankIdx + 1);

            if (getanswer) return;

            sudoku[r][c] = 0;
        }
    }

    private static boolean checkSudoku(int r, int c) {
        boolean[] visited;

        visited = new boolean[10];

        for (int tr = 0; tr < 9; tr++) {
            if (sudoku[tr][c] > 0 && visited[sudoku[tr][c]]) return false;

            visited[sudoku[tr][c]] = true;
        }

        visited = new boolean[10];

        for (int tc = 0; tc < 9; tc++) {
            if (sudoku[r][tc] > 0 && visited[sudoku[r][tc]]) return false;

            visited[sudoku[r][tc]] = true;
        }

        visited = new boolean[10];

        for (int tr = (r / 3) * 3; tr < ((r / 3) * 3) + 3; tr++) {
            for (int tc = (c / 3) * 3; tc < ((c / 3) * 3) + 3; tc++) {
                if (sudoku[tr][tc] > 0 && visited[sudoku[tr][tc]]) return false;

                visited[sudoku[tr][tc]] = true;
            }
        }

        return true;
    }
}
