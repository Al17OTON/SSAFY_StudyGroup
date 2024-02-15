package 목요빈.Week04;

import java.io.*;
import java.util.*;

public class BOJ_15686_치킨배달 {

	static int N, M, min;
	static List<Pos> home, ch;
	static boolean[] selected;
	static int[] chicken;

	// 1: 집, 2: 치킨집
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // <= 50
		M = Integer.parseInt(st.nextToken()); // <= 13

		home = new LinkedList<>();
		ch = new LinkedList<>();
		
		min = Integer.MAX_VALUE;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int place = Integer.parseInt(st.nextToken());
				if (place == 1) { // 집
					home.add(new Pos(i, j));
				} else if (place == 2) { // 치킨집
					ch.add(new Pos(i, j));
				}
			}
		}
		
		selected = new boolean[ch.size()];
		chicken = new int[M];
		comb(0, 0);
		System.out.println(min);
	}

	static void comb(int n, int idx) {
		// n : 뽑은 수 개수, idx : 치킨 배열 순회 인덱스
		if(n == M) {
			int dis = 0;
			for (Pos pos : home) {
				int home_dis = Integer.MAX_VALUE;
				for (int i : chicken) {
					home_dis = Math.min(home_dis, Math.abs(ch.get(i).r - pos.r) + Math.abs(ch.get(i).c - pos.c));
				}
				dis += home_dis;
			}
			min = Math.min(min, dis);
			return;
		}
		
		for(int i = idx; i<ch.size(); i++) {
			if(selected[i]) continue;
			selected[i] = true;
			chicken[n] = i;
			comb(n+1, i);
			selected[i] = false;
		}
	}
}