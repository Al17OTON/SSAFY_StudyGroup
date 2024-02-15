package Week03;

import java.util.Scanner;

public class BOJ_5904_Moo게임 {

    static int N;
    static int Len = 0;

    //getLength함수로 N이 포함된 K 값을 찾는다. 그 K값을 가지고 N값을 가진 실제 f(k - i) 위치를 찾는다.
    //그 역할은 recursive함수가 맡는다. 
    //전역 변수 Len은 f(K)의 마지막 문자부터 중위순회로 문자열 순서로 Moo를 방문하여 실제 N이 포함된 곳을 찾는다.
    //찾았다면 출력한 후 탈출한다.
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);        

        N = sc.nextInt();
        
        getLength(0, 3);
    }

    static void getLength(int k, int len) {
        if(2 * len + k + 4 >= N) {
            if(len + 1 == N) System.out.println('m');
            else {
                Len = len + k + 4;
                recursive(k);
            }
            return;
        }

        getLength(k + 1, 2 * len + k + 4);
    }

    static boolean recursive(int k) {
        if(k == 0) {
            if(Len + 3 >= N) {
                if(Len + 1 == N) System.out.println("m");
                else System.out.println("o");
                return true;
            }
            Len += 3;
            return false;
        }
        
        if(recursive(k - 1)) return true;
        if(Len + k + 3 >= N) {
            if(Len + 1 == N) System.out.println("m");
            else System.out.println('o');
            return true;
        }
        Len += k + 3;
        return recursive(k - 1);
    }
}
