package 강지우.Week03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준_5904_moo_게임 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String[][] arr;
    static StringBuilder sb = new StringBuilder();
    static int n;
    public static void main(String[] args) throws NumberFormatException, IOException{
//		sb.append("3");
        n = Integer.parseInt(br.readLine());
        rc(4);
        int sum = 0;
        String word = n== 1 ? "m" :"o";
        for(int i = 0; i<sb.length(); i++) {
            sum = sum + sb.charAt(i) -'0';
            if(sum + 1 == n) {
                word = "m";
                break;
            }
        }

        System.out.println(word);

    }
    private static void rc(int i) {
        String word = "" +sb;
        if(sb.length() >= n) {
            return;
        }
        sb.append("3"+i);
        sb.append(word);
        rc(i+1);

    }
}
