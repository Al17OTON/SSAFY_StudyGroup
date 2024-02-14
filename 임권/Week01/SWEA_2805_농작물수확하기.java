package Week01;

import java.util.Scanner;

public class SWEA_2805_농작물수확하기 {
    public static void main(String[] args) {
        int[][] map = new int[49][49];

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for(int t = 1; t <= T; t++) {
            int N = sc.nextInt();
            int result = 0;
            int mid = (N / 2) + 1;
            for(int i = 1; i <= N; i++) {
                int count = (i * 2) - 1;
                if(i > mid) count = ((mid - (i - mid)) * 2) - 1;
                int start = mid - ((count - 1) / 2);
                String input = sc.next();
                for(int j = 1; j <= N; j++) {
                    int tmp = input.charAt(j - 1) - '0';
                    if(start <= j && count > 0) {
                        count--;
                        result += tmp;
                    }
                }
            }
            System.out.println("#" + t + " " + result);
        }
    }
}
