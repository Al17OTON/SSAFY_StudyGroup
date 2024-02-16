package 강지우.Week04;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class SWEA_5658_보물상자_비밀번호 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int t,n,m;
    static Deque<String> q;
    static Set<Integer> treeset;
    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        for (int s = 1; s <= t; s++) {
            treeset = new TreeSet<>();
            q = new LinkedList();
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            String word = br.readLine();

            for (int i = 0; i < n; i++) {
                q.add(String.valueOf(word.charAt(i)));
            }

            getHex(n, word);
            for (int i = 0; i < n/4; i++) {
                q.add(q.poll());
                getHex(n, String.join("",q));
            }

            ArrayList<Integer> ans = new ArrayList<>(treeset);
            Collections.reverse(ans);
            System.out.printf("#%d %d\n",s,ans.get(m-1));
        }



    }

    private static void getHex(int n, String word) {
        for (int i = 0; i < n; i+= n /4) {
            treeset.add(Integer.parseInt(word.substring(i,i+ n /4),16));
        }
    }


}

//5
//12 10
//1B3B3B81F75E
//16 2
//F53586D76286B2D8
//20 14
//88F611AE414A751A767B
//24 16
//044D3EBA6A647B2567A91D0E
//28 11
//8E0B7DD258D4122317E3ADBFEA99