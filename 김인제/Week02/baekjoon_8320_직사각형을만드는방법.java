import java.util.Scanner;

/*
 * BOJ 8320 직사각형을 만드는 방법
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int answer = 0;
        
        for (int i = 1; i < (int)Math.sqrt(n) + 1; i++) {
            answer += (n / i) - i + 1;
        }

        System.out.println(answer);
        
        sc.close();
    }
}
