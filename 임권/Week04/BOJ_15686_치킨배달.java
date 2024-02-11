package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class CAH {		//Chicken And House
	int r, c;
	
	CAH(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class BOJ_15686_치킨배달 {
	
	static int N, M;
	static List<CAH> ch = new ArrayList<>();		//치킨집 좌표 목록
	static List<CAH> home = new ArrayList<>();		//집 좌표 목록
	static List<CAH> sel = new ArrayList<>();		//선택된 치킨집 좌표 목록
	static int minLen = Integer.MAX_VALUE;			//최소 치킨 거리
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				int t = Integer.parseInt(st.nextToken());
				if(t == 1) home.add(new CAH(r, c));
				else if(t == 2) ch.add(new CAH(r, c));
			}
		}
		
		dfs(0);
		
		
		System.out.println(minLen);
		
	}
	
	static void dfs(int idx) {		//idx는 전체 치킨 목록의 인덱스 역할
		if(sel.size() == M) {		//치킨 선택이 M개가 되어 끝나면 도시의 치킨 거리 구하기
			minLen = Math.min(minLen, calcChickenLen());
			return;
		}
		if(idx >= ch.size()) return;	//치킨집을 M개만큼 못골랐는데 더이상 고를 수 없다면 돌아가기
		
		sel.add(ch.get(idx));			//치킨집 선택
		dfs(idx + 1);
		sel.remove(sel.size() - 1);		//치킨집 선택 해제
		dfs(idx + 1);
	}
	
	static int calcChickenLen() {			//도시의 치킨 거리값을 구한다
		int[] lens = new int[home.size()];	//모든 집에 대한 치킨거리의 최소값을 기록
		int result = 0;
		Arrays.fill(lens, Integer.MAX_VALUE);
		
		for(int i = 0; i < sel.size(); i++) {			//선택된 치킨집을 모두 방문하여 가장 최소 거리가 나오는 값 기록
			for(int j = 0; j < home.size(); j++) {
				lens[j] = Math.min(lens[j], calcDistance(home.get(j), sel.get(i)));	
			}
		}
		
		for(int i = 0; i < lens.length; i++) {	//최소값들로 이루어진 치킨거리를 합하여 도시의 치킨거리를 구하기
			result += lens[i];
		}
		return result;
	}
	
	static int calcDistance(CAH a, CAH b) {		//거리계산
		return Math.abs(a.r - b.r) + Math.abs(a.c - b.c);
	}

}
