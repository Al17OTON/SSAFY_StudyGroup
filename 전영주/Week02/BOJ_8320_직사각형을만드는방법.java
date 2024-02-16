package 전영주.Week02;

import java.io.*;
import java.util.Scanner;

public class BOJ_8320_직사각형을만드는방법 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int answer=0;
		for (int i = 1; i <= n; i++) {
			for (int j = i; i * j <= n; j++) {
				answer++;
			}
		}
		System.out.println(answer);		
	}
}
/*
* n<10_000 만
* 이동 회전 가능-> 길이로 구분 가능 가로 세로 구분 x
* 즉 a*b 직사각형을 만드는데 a와 b는 같을 수 있지만 같은 쌍이 있을 순 없다
* 넓이가 1~10000인 직사각형 개수
* for int i, int j ; i*j<n;
* visited[i][j]해서 i와 j 값 가능한지 확인 여부 ?
* => 그냥 j를 i 보다 크게 하면 중복 방지가 됨
* 
*/
// //다른 사람 코드: 이해가 안가는군..
//근데 복붙해서 돌렸는데 왜 난 124ms 이고 그 사람은 68ms일까,,
//스캐너랑 버퍼는 입출력이 많지 않으면 100ms정도 차이남 별로 안중요
//int N_sqrt = (int)Math.sqrt(N);
//for (int i = 1; i <= N_sqrt; i++) {
//	sum += N/i - (i-1);
//}