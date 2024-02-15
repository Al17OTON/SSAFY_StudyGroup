package 강지우.Week03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 참고
public class 백준_2447_별찍기10 {

        static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        static String[][] arr;
        static StringBuilder sb = new StringBuilder();
        public static void main(String[] args) throws NumberFormatException, IOException{
            int n = Integer.parseInt(br.readLine());
//		int n = 27;
            arr = new String[n][n];
            for(int i = 0; i<n; i++) {
                Arrays.fill(arr[i]," ");
            }
//
            star(0,0,n);
            for(int i = 0; i< n; i++) {
                System.out.println(String.join("", arr[i]));
            }
        }
        private static void star(int i, int j, int n) {
            if(n==1) {
//			sb.append("*");
                arr[i][j] = "*";
                return;
            }

            for(int x = 0; x<3; x++ ) {
                for(int y = 0; y<3; y++) {
                    if(x==1 && y==1) {
//					sb.append(" ");
                        continue;
                    }
                    star(i+(n/3)*x,j+(n/3)*y,n/3);


                }

            }

        }
    }

//    풀이
//    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    static StringBuilder sb = new StringBuilder();
//    public static void main(String[] args) throws IOException {
//        int n = Integer.parseInt(br.readLine());
//
//        for (int i = 0; i < n; i++) {
//            for(int j = 0; j<n; j++){
//                star(i,j,n);
//            }
//            sb.append("\n");
//        }
//        System.out.println(sb);
//
//    }
//    private static void star(int i, int j, int n) {
//        if(i%3==1 && j%3==1){
//            sb.append(" ");
//        }else{
//            if(n==1){
//                sb.append("*");
//
//            }else{
//                star(i/3,j/3,n/3);
//            }
//        }
//    }
//}