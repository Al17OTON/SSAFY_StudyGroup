package hw;

import java.util.*;

public class SWEA_4050_재관이의대량할인 {
     
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
         
        for(int t = 1; t <= T; t++) {
            int N = sc.nextInt(); // <= 100,000
            int cost = 0;
            Integer[] clothes = new Integer[N];
            for (int i = 0; i < N; i++) {
                clothes[i] = sc.nextInt();
            }
             
            Arrays.sort(clothes, Comparator.reverseOrder());
 
            for (int i = 0; i < N; i ++) {
                if(i % 3 == 2) continue;
                cost += clothes[i];
            }
            System.out.printf("#%d %d\n", t, cost);
             
        }
        sc.close();
    }
}