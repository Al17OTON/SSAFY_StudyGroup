package Week07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17136_색종이붙이기 {
	static int SIZE = 10, min = Integer.MAX_VALUE;
	static boolean[][] map;
	
	//사용가능한 색종이 수
	static int[] paper = {5,5,5,5,5};
	
	static int[] dr = {1, 0};
	static int[] dc = {0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map = new boolean[SIZE][SIZE];
		
		for(int i = 0; i < SIZE; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < SIZE; j++) {
				if(st.nextToken().charAt(0) == '1') {
					map[i][j] = true;
				}
			}
		}
		
		permutation(0, 0, 0, map);
		
		if(min == Integer.MAX_VALUE) min = -1;
		System.out.println(min);
	}
	
	static void permutation(int r, int c, int used,boolean[][] m) {
		if(used >= min) return;	//가지치기
		//basis part
		if(r == SIZE) {
			//최소 색종이 수 갱신
			min = used;
			return;
		}
		//행의 끝에 도달하면 다음 열로 이동
		if(c == SIZE) {
			permutation(r + 1, 0, used, m);
			return;
		}
		//0인 부분을 만나면 다음 행으로 이동
		if(!m[r][c]) {
			permutation(r, c + 1, used, m);
			return;
		}
		
		//1인 부분이라면 남아있는 색종이 중 5에서 1까지의 색종이를 붙여본다.
		for(int i = paper.length - 1; i >= 0; i--) {
			if(paper[i] > 0) {
				paper[i]--;
				fill(r, c, i + 1, used + 1,copy(m));
				paper[i]++;
			}
		}
	}
	
	//bfs로 색종이 채워넣기
	static void fill(int r, int c, int len, int used, boolean[][] m) {
		//색종이가 경계를 벗어나면 불가능
		if(r + len > SIZE || c + len > SIZE) return;
		
		//제거되어야할 1인 공간의 수
		int need = len * len;
		Queue<int[]> q = new LinkedList();
		q.offer(new int[] {r, c});
		m[r][c] = false;
		need--;
		
		while(!q.isEmpty()) {
			int[] tmp = q.poll();
			
			for(int i = 0; i < dr.length; i++) {
				int nr = tmp[0] + dr[i];
				int nc = tmp[1] + dc[i];
				
				if(nr >= r + len || nc >= c + len) continue;
				if(!m[nr][nc]) continue;
				
				m[nr][nc] = false;
				need--;
				q.offer(new int[] {nr, nc});
			}
		}
		//만약 중간에 0이 있었다면 1의 수가 len * len이 되지 못해 해당 색종이를 붙이는것은 불가능하다.
		if(need != 0) return;
		
		//다음 열로 이동
		permutation(r, c + len, used, m);
	}
	
	
	
	static boolean[][] copy(boolean[][] o) {
		boolean[][] copy = new boolean[SIZE][];
		for(int i = 0; i < SIZE; i++) {
			copy[i] = o[i].clone();
		}
		return copy;
	}

}


















