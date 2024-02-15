import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 한자리만 틀리는지
// 두개의 값중 최소값에서 시작
public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static int t,ans;
    static int[] lst2,lst3;
    public static void main(String[] args) throws NumberFormatException, IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        for(int tc = 1; tc<=t; tc++) {
            String word2 = br.readLine();
            String word3 = br.readLine();
            lst2 = new int[word2.length()];
            lst3 = new int[word3.length()];

            for (int i = 0; i < lst2.length; i++) {
                lst2[i] = word2.charAt(i) - '0';
            }

            for (int i = 0; i < lst3.length; i++) {
                lst3[i] = word3.charAt(i) - '0';
            }

            ans = getSolve();
            System.out.printf("#%d %d\n",tc, ans);



        }

    }

    //   주어진 입력값의 2진수, 3진수 경우의 수 모두 구한뒤 값 비교`
    private static int getSolve() {
        int[] two = new int[lst2.length];
        int[][] third = new int[lst3.length][2];

        for (int i = 0; i < lst2.length; i++) {
            int[] n_lst2 = lst2.clone();
            n_lst2[i] = lst2[i] == 0 ? 1:0;
            two[i] = chage(n_lst2, 2);
        }

        for (int i = 0; i < lst3.length; i++) {
            int[] n_lst3 = lst3.clone();
            for (int j = 0; j < 2; j++) {
                if(n_lst3[i] == 0){
                    n_lst3[i] = 1;
                } else if (n_lst3[i] == 1) {
                    n_lst3[i] = 2;
                } else{
                    n_lst3[i] = 0;
                }
                third[i][j] = chage(n_lst3, 3);
            }
        }

        for (int i = 0; i < lst2.length; i++) {
            for (int j = 0; j < lst3.length; j++) {
                for (int k = 0; k < 2; k++) {
                    if(two[i] == third[j][k]){
                        return two[i];
                    }
                }
            }
        }
        return -1;
    }

    //   10진수로 변환
    private static int chage(int[] lst, int n) {
        int num = 0;
        for (int i = 0; i < lst.length; i++) {
            num += Math.pow(n, lst.length - 1 - i) * lst[i];
        }

        return num;
    }
}