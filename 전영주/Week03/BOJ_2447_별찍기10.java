package 전영주.Week03;

import java.io.*;
import java.util.Scanner;

public class BOJ_2447_별찍기10 {
    static int n;
    static char[][] arr;
    static BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        arr=new char [n][n];
        for (int i = 0; i <n; i++) {
            for (int j = 0; j < n; j++) {
                checkStar(i, j,n);// 현재 i,j 에 따라 별을 찍을 지 확인하는 메서드 호출
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
        sc.close();
    }
    private static void checkStar(int x,int y,int num) throws IOException {
        // 빈칸을 넣어야하는 경우.각 num에 따라 num/3의 5번째가 빈칸이다.
        if(x%3==1&&y%3==1)bw.write(" ");
        else {
            if(num==1){
                //basis part
                bw.write("*");
            }else {
                checkStar(x/3, y/3, num/3);
            }
        }
        
    }
}
/*
 * 재귀, n<3^8, 시간적 제약 낮음
 * n->n/3 ->n/3/3 ,.. 하향식 재귀 
 * recursive[n]=recursive[n/3]+..+빈칸*n/3+recursive[n/3]+...
 * 시작 인덱스를 알아야 패턴을 쓸 수 있음
 * i j num
 *  0 0 27
 *  0 0 9
 *  0 0 3
 *  0 0 1
 *  -> num이 1이기 때문에 * 출력
 *  0 0 27
 *  0 0 9
 *  0 0 3
 *  0 0 1
 *  ->num이 1이기 때문에 *출력
 *  . . .
 *  1 1 27
 *  -> 28번째 줄에 의해 빈칸 출력
 *  1 2 27
 *  0 0 9
 *  0 0 3
 *  0 0 1
 *  -> num이 1이기 때문에 * 출력
 *  . . .
 *  3 3 27
 *  1 1 9
 *  -> 28번째 줄에 의해 빈칸 출력
 *  3 4 27
 *  1 1 9
 *  -> 28번째 줄에 의해 빈칸 출력
 *  . . .
 *  8 9 27
 *  2 3 9
 *  0 1 3
 *  0 0 1
 *  -> num이 1이기 때문에 * 출력
 *  9 9 27
 *  3 3 9
 *  1 1 3
 *  -> num이 1이기 때문에 * 출력
 */

/*
 * 내 코드는 일괄된 처리를 해결하지 못함
 * 별이라던지 빈칸의 범위는 패턴이 있기 때문에 for문으로 처리 가능할 듯
 *------
 * 다른 사람 코드
 * private static void star(int x, int y, int n, boolean blank) { 
        // 공백인 곳에 공백처리
        if (blank) {
            for (int i = x; i < x + n; i++) {
                for (int j = y; j < y + n; j++) {
                    arr[i][j] = ' ';
                }
            }
            return;
        }

        if (n == 1) {
            arr[x][y] = '*';
            return;
        }

        int recursiveCnt = n / 3;
        int count = 0;
        for (int i = x; i < x + n; i += recursiveCnt) {
            for (int j = y; j < y + n; j += recursiveCnt) {
                count++;
                if (count == 5) { // 5번째 반복에서 공백 출력
                    star(i, j, recursiveCnt, true); //재귀함수 
                } else {
                    star(i, j, recursiveCnt, false);

                }
            }
        }

    }

 */