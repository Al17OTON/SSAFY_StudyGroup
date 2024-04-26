package com.algorithm.solution.boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* BOJ 6987. 월드컵 */
public class Problem6987 {
    static boolean answer;
    static int[][] games = new int[6][3];
    static int[][] newGames = new int[6][3];

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input/boj/Input6987.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = 4;

        for (int tc = 1; tc < T + 1; tc++) {
            st = new StringTokenizer(br.readLine());

            for (int r = 0; r < games.length; r++) {
                for (int c = 0; c < games[r].length; c++) {
                    games[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            answer = false;

            getTrue(0, 1);

            System.out.print(answer ? "1 " : "0 ");
        }

        br.close();
    }

    private static void getTrue(int home, int away) {
        if (answer) return;

        if (away == 6) {
            home++;
            away = home + 1;
        }

        if (home == 5) {
            answer = checkEquals(games, newGames);

            return;
        }

        newGames[home][0]++;
        newGames[away][2]++;
        getTrue(home, away + 1);
        newGames[home][0]--;
        newGames[away][2]--;

        newGames[home][2]++;
        newGames[away][0]++;
        getTrue(home, away + 1);
        newGames[home][2]--;
        newGames[away][0]--;

        newGames[home][1]++;
        newGames[away][1]++;
        getTrue(home, away + 1);
        newGames[home][1]--;
        newGames[away][1]--;
    }

    private static boolean checkEquals(int[][] arr1, int[][] arr2) {
        for (int r = 0; r < arr1.length; r++) {
            for (int c = 0; c < arr1[r].length; c++) {
                if (arr1[r][c] != arr2[r][c]) return false;
            }
        }

        return true;
    }
}
