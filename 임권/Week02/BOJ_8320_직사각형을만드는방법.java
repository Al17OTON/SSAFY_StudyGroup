package Week02;

import java.util.Scanner;

public class BOJ_8320_직사각형을만드는방법 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int result = 0;

        for(int i = 1; N / i >= i; i++) {
            result += (N / i) - (i - 1);
        }
        System.out.println(result);
    }
}
