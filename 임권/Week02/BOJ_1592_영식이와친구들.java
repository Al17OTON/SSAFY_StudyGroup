package Week02;

import java.util.Scanner;

/*
 * 그냥 시뮬레이션으로 하는 문제라 생각하여 주어진 요구사항에 맞추어 코딩하였습니다.
 */

public class BJ_1592_영식이와친구들 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        int L = sc.nextInt();

        simulation(N, M, L);
        
    }

    static void simulation(int N, int M, int L) {
        int[] player = new int[N];
        int count = 0, idx = 0;  //시작은 첫번째 자리 플레이어 부터

        while(true) {
            player[idx]++;      //받은 횟수 기록
            if(player[idx] == M) break;

            if(player[idx] % 2 == 0) {    //짝수번 받은 경우
                idx = idx - L;
                if(idx < 0) idx = N - Math.abs(idx);    //음수로 범위를 넘어가는 경우, 넘어간 만큼 뒤에서 세기
            }
            else {
                idx = (idx + L) % N;    //mod 연산으로 범위 제한
            }
            count++;            //던진 횟수 기록
        
        }

        System.out.println(count);
    }

}
