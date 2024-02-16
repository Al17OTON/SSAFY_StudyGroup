import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

//	갈수 있을경우 체크
//	지도 표시
//	지도 원복
public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static int t,n,max_cnt, min_len;
    static int[][] arr, core_lst;
    static List<int[]> list;
    static int[][] dir = {{0,1},{1,0},{-1,0},{0,-1}};
    static boolean[] v;
    public static void main(String[] args) throws NumberFormatException, IOException {
        //System.setIn(new FileInputStream("src/jiu/Solution.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            n = Integer.parseInt(br.readLine());
            arr = new int[n][n];
            list = new ArrayList<>();
            max_cnt = 0;
            min_len = Integer.MAX_VALUE;
            v = new boolean[n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    if(arr[i][j] == 1) {
                        if(i == 0 || j==n-1 || i == n-1 || j == 0) {
                            continue;
                        }else {
                            list.add(new int[] {i,j});
                        }
                    }
                }
            }

//			core_lst = new int[list.size()][2];
//			System.out.println(list.size());
            per(0,0);

//			print();
//			System.out.println(max_cnt);
            System.out.printf("#%d %d\n",tc,min_len);
        }
    }


    private static void per(int cnt, int sum) {
        if(cnt == list.size()) {
            if(sum>max_cnt) {	// 최대 값 갱신
                max_cnt = sum;
                min_len = getLen();
            }else if(sum==max_cnt){		// 최대값이 같은경우 길이 비교해서 최신화
                min_len = Math.min(min_len, getLen());
            }
            return;
        }

        int[] core = list.get(cnt);
        int x = core[0];
        int y = core[1];
        for (int[] d : dir) {
            if(check(x,y,d)) {
                move(x,y,d,2);
                per(cnt+1,sum+1);
                move(x,y,d,0);
            }else {
                per(cnt+1,sum);
            }

        }

    }


    private static boolean check(int x, int y, int[] d) {
        int dx = x;
        int dy = y;

        while(true) {
            int nx = dx + d[0];
            int ny = dy + d[1];

            if(nx<0 || nx>=n || ny<0 || ny>=n) {
                break;
            }
            if(arr[nx][ny] != 0) {
                return false;
            }

            dx = nx;
            dy = ny;

        }
        return true;
    }


    private static int getLen() {
        int len = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(arr[i][j] == 2) {
                    len++;
                }
            }
        }
        return len;
    }


    private static void move(int x, int y, int[] d, int k) {
        int dx = x;
        int dy = y;

        while(true) {
            int nx = dx + d[0];
            int ny = dy + d[1];
            if(nx<0 || nx>=n || ny<0 || ny>=n) {
                break;
            }
            arr[nx][ny] = k;
            dx = nx;
            dy = ny;
        }

    }


    private static void print() {
        for (int[] lst : arr) {
            System.out.println(Arrays.toString(lst));
        }
        System.out.println();

    }

}
