import java.io.*;
import java.util.*;

public class BOJ_17822_원판돌리기 {

	static int N, M, T;
	static int[][] circle;
	static Rotate[] rotate;
	
	static class Rotate{
		int x, d, k;

		public Rotate(int x, int d, int k) {
			super();
			this.x = x; // x 배수 원판
			this.d = d; // 방향(0: 시계, 1: 반시계)
			this.k = k; // k칸 회전
		}
	}
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 원판 개수
		M = Integer.parseInt(st.nextToken()); // 원판에 적힌 수 <= 50
		T = Integer.parseInt(st.nextToken()); // 회전 수

		circle = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				circle[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		rotate = new Rotate[T];      
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine()); 
			int x = Integer.parseInt(st.nextToken()); // x 배수 원판
			int d = Integer.parseInt(st.nextToken()); // 방향(0: 시계, 1: 반시계)
			int k = Integer.parseInt(st.nextToken()); // k칸 회전
			rotate[i] = new Rotate(x, d, k);
		}
		
		for (int i = 0; i < T; i++) {
			Rotate cur = rotate[i];
			// 돌리기
			for(int j = cur.x; j <= N; j += cur.x) {
				 circle[j-1] = rotate(circle[j-1], cur.d, cur.k);
			}
			
			// 인접 + 같은 수 확인 및 지우기
			int[][] temp = new int[N][M];
			for (int n = 0; n < N; n++) {
				temp[n] = circle[n].clone();
			}
			
			boolean near = false;
			// 원판 내
			for (int n = 0; n < N; n++) { // 원판 번호
				for (int m = 1; m < M-1; m++) { // 원판 내 숫자
					if(temp[n][m] == 0) continue;
					if(temp[n][m] == temp[n][m-1]) {
						circle[n][m] = circle[n][m-1] = 0;
						near = true;
					}
					if(temp[n][m] == temp[n][m+1]) {
						circle[n][m] = circle[n][m+1] = 0;
						near = true;
					}
				}
				
				if(temp[n][0] == temp[n][1] && temp[n][0] != 0) {
					circle[n][0] = circle[n][1] = 0;
					near = true;
				}
				if(temp[n][0] == temp[n][M-1] && temp[n][0] != 0) {
					circle[n][0] = circle[n][M-1] = 0;
					near = true;
				}
			}
			// 원판 간
			for (int m = 0; m < M; m++) { // 원판 내 숫자
				for (int n = 1; n < N-1; n++) { // 원판 번호
					if(temp[n][m] == 0) continue;
					if(temp[n][m] == temp[n-1][m]) {
						circle[n][m] = circle[n-1][m] = 0;
						near = true;
					}
					if(temp[n][m] == temp[n+1][m]) {
						circle[n][m] = circle[n+1][m] = 0;
						near = true;
					}
				}
				
				if(temp[0][m] == temp[1][m] && temp[0][m] != 0) {
					circle[0][m] = circle[1][m] = 0;
					near = true;
				}
			}
			
			if(!near) {
				int[] res = sum();	
				double avg = res[1] / (res[0] * 1.0); 
				
				for (int n = 0; n < N; n++) { // 원판 번호
					for (int m = 0; m < M; m++) { // 원판 내 숫자
						if(circle[n][m] == 0) continue;
						if(circle[n][m] > avg) circle[n][m] -= 1;
						else if(circle[n][m] < avg) circle[n][m] += 1;
					}
				}
			}
		}
		
		System.out.println(sum()[1]);
	}
	
	static int[] sum() {
		int cnt = 0;
		int sum = 0;
		
		for (int n = 0; n < N; n++) { // 원판 번호
			for (int m = 0; m < M; m++) { // 원판 내 숫자
				if(circle[n][m] == 0) continue;
				cnt += 1;
				sum += circle[n][m];
			}
		}
		
		return new int[] {cnt, sum};
	}
	
	static int[] rotate(int[] circle, int dir, int k) {
		int[] temp = new int[M];
		int idx = 0;
		
		if(dir == 0) idx = M-k;
		else idx = k;
		
		for (int i = 0; i < M; i++) {
			temp[i] = circle[(i+idx) % M];
		}
		return temp;
	}
}
