package Week03;

import java.util.Scanner;

public class BOJ_2991_사나운개 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();
        int D = sc.nextInt();
        int P = sc.nextInt();
        int M = sc.nextInt();
        int N = sc.nextInt();
        
        int a = 0, b = 0, c = 0;

        //도착 시간 % 댕댕이의 공격 주기 = 댕댕이의 공격 주기안에 도착 시간 위치를 알 수 있다.
        //그렇게 구한 도착 시간 위치가 공격 기간안에 포함된다면 공격 횟수 추가
        //하지만 도착 시간 위치가 0일 경우는 댕댕이가 휴식하고 있는 시간이므로 위에서 증가한 공격 횟수 차감
        //배열로 타임라인을 계산하여 공격 여부를 체크하는 방법도 있음.
        if(P % (A + B) <= A) a++;
        if(P % (C + D) <= C) a++;
        if(P % (A + B) == 0) a--;
        if(P % (C + D) == 0) a--;

        if(M % (A + B) <= A) b++;
        if(M % (C + D) <= C) b++;
        if(M % (A + B) == 0) b--;
        if(M % (C + D) == 0) b--;

        if(N % (A + B) <= A) c++;
        if(N % (C + D) <= C) c++;
        if(N % (A + B) == 0) c--;
        if(N % (C + D) == 0) c--;
        

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }
}
