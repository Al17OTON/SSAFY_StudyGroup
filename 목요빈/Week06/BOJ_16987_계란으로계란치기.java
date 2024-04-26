package 목요빈.Week06;

import java.io.*;
import java.util.*;

public class BOJ_16987_계란으로계란치기 {

	static int N, max;
	static Egg[] eggs, temp;
	static boolean[] selected, broken;
	
	static class Egg{
		int dur, weight;

		public Egg(int dur, int weight) {
			super();
			this.dur = dur;
			this.weight = weight;
    }
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); // <= 8
		eggs = new Egg[N];
		broken = new boolean[N];
		selected = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			eggs[i] = new Egg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		max = 0;
		dfs(0, 0);
		System.out.println(max);
	}
	
	static void dfs(int left, int count) {
		// 들 계란 인덱스
		if(left == N) { // 가장 오른쪽 계란 차례이면
//			System.out.println(Arrays.toString(eggs));
			max = Math.max(max, count);
			return;
		}
		
		boolean flag = true; // 현재 계란은 안깨져있는데 다른 계란들 전부 깨져있음
		
		for (int i = 0; i < N; i++) {
			if(i == left) continue;
			if(!broken[i]) flag = false;
		}
		
		if(flag) {
			max = Math.max(max, count);
			return;
		}
		// 들어야 하는 계란이 깨져있으면 다음으로 넘어가기
		if(broken[left]) {
			dfs(left+1, count);
			return;
		}
		
		for (int right = 0; right < N; right++) {
			int cur_count = 0; 
			if(right == left) continue;
			if(broken[right]) continue; // 놓여진 계란이 깨져있으면 다음으로
			
			eggs[left].dur -= eggs[right].weight;
			eggs[right].dur -= eggs[left].weight;
			
			if(eggs[left].dur <= 0) {
				cur_count += 1;
				broken[left] = true;
			}
			
			if(eggs[right].dur <= 0) {
				cur_count += 1;
				broken[right] = true;
			}
			
			dfs(left+1, count + cur_count);
			
			if(eggs[left].dur <= 0) broken[left] = false;
			if(eggs[right].dur <= 0) broken[right] = false;
			eggs[left].dur += eggs[right].weight;
			eggs[right].dur += eggs[left].weight;
		}
	}
}
