import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// n-1 만큼 쪼개야 하네
/*
    1089
    1 89
    8 9
    7 2
    1 4
    4
    108 9
    972
 */
public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static int t,ans;
    static String word;
    static int[] arr,res;
    static boolean[] v;
    static List<Integer> per;
    static List<Integer> test1;
    static List<List<Integer>> test2;
    public static void main(String[] args) throws NumberFormatException, IOException {
//        br = new BufferedReader(new InputStreamReader(System.in));
//        t = Integer.parseInt(br.readLine());
//        for (int tc = 1; tc <= t; tc++) {
//
//
//            word = br.readLine();
//            sel = new int[word.length()-1];
//            v = new boolean[word.length()-1];
//            for (int i = 1; i < word.length(); i++) {
//                sel[i-1] = i;
//            }
//            for (int i : sel) {
//                System.out.print(i);
//            }
//        dfs(1);

        word = "123";


//        for (List<Integer> integers : per1) {
//            System.out.println(integers);
//        }
//        dfs(0, "123");
        arr = new int[word.length()-1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

        v = new boolean[arr.length];

        powerSet(0);
    }
    private static void powerSet(int cnt){
        if(cnt == arr.length){
            List<Integer> set = new ArrayList<>();
            for (int i = 0; i < arr.length; i++) {
                if (v[i]){
                    set.add(arr[i]);
                }
            }
            cal(set, word);
            return;
        }
        v[cnt] = true;
        powerSet(cnt+1);
        v[cnt] = false;
        powerSet(cnt+1);
    }

    private static void cal(List<Integer> set, String word) {
        int idx = 0;
        int num = 1;
        for (int j = 0; j < set.size(); j++) {
            int qwer = Integer.parseInt(word.substring(idx, set.get(j) + 1));
//            System.out.println(qwer);
            num *= qwer;
            idx = set.get(j)+1;
        }
        num *= Integer.parseInt(word.substring(idx));

        dfs(0,num);
    }

    private static void dfs(int cnt, int num) {
        if(num / 10 == 0){
            ans = Math.max(ans,cnt);
            return;
        }
    }
}
//12 3 4
//1 2 3 4