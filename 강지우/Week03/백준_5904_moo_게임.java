package 강지우.Week03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 메모리 초과..
// 재풀이 예정
public class 백준_5904_moo_게임 {


    public class Main {
        static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        static StringBuilder sb = new StringBuilder();
        static int n;

        public static void main(String[] args) throws NumberFormatException, IOException {
            n = Integer.parseInt(br.readLine());
            rc(4);
            int sum = 0;
            String word = (n == 1) ? "m" : "o";

            for (int i = 0; i < sb.length(); i++) {
                sum = sum + sb.charAt(i) - '0';
                if (sum + 1 == n) {
                    word = "m";
                    break;
                }
            }

            System.out.println(word);
        }

        private static void rc(int i) {
            if (sb.length() > n) {
                return;
            }
            sb.append("3" + i);
            rc(i + 1);
        }
    }
}