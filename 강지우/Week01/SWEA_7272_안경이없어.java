package 강지우.Week01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_7272_안경이없어 {

    public final static String ZERO = "CEFGHIJKLMNSTUVWXYZ";
    public final static String ONE = "ADOPQR";
    public final static String TWO = "B";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int cnt = Integer.parseInt(br.readLine());
        for(int i = 1; i<=cnt; i++){
            st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            String b = st.nextToken();

            boolean ans = check(a, b);

            if(ans){
                System.out.printf("#%d SAME\n",i);
            }else {
                System.out.printf("#%d DIFF\n",i);
            }
        }



    }
    public static boolean check(String a, String b){
        if (a.length() != b.length()){
            return false;
        }else{
            for(int i = 0; i<a.length();i++){
                String n = Character.toString(a.charAt(i));
                String m = Character.toString(b.charAt(i));
                if((ZERO.contains(n) && ZERO.contains(m)) || (ONE.contains(n) && ONE.contains(m)) || (TWO.contains(n) && TWO.contains(m)) ) {
                    continue;
                }else{
                    return false;
                }
            }
        }
        return true;
    }
}