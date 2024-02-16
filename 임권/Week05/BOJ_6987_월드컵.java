package Week05;

import java.util.Scanner;

public class BOJ_6987_월드컵 {
	
	static int[][] league;
	static int[] result;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		result = new int[4];
		
		for(int round = 0; round < 4; round++) {
			league = new int[6][3];
			
			for(int i = 0; i < 6; i++) {
				league[i][0] = sc.nextInt();
				league[i][1] = sc.nextInt();
				league[i][2] = sc.nextInt();
			}
			
			result[round] = dfs(new int[6][3], 0, 1) ? 1 : 0;
		}
		
		for(int i = 0; i < 4; i++) {
			System.out.print(result[i] + " ");
		}
	}
	
	//match는 현재 경기 결과, A는 현재 국가, B는 경기를 치룰 국가
	static boolean dfs(int[][] match, int A, int B) {
        //6개국 모두 경기를 한번씩 치뤘다면 종료
		if(A == 6) {
			for(int i = 0; i < 6; i++) {
				for(int j = 0; j < 3; j++) {
					if(league[i][j] != match[i][j]) return false;
				}
			}
			return true;
		}
		
        //A국가가 다른 5개국과 경기를 다 치뤘다면 다음 국가로 넘기기
        //이때 B를 A + 2로 하여 중복 경기를 치루지 않도록 한다.
		if(B == 6) {
			if(dfs(match, A + 1, A + 2)) return true;
		} else {
		
		
			//이긴경우
			match[A][0]++;
			match[B][2]++;
			if(match[A][0] <= league[A][0] && match[B][2] <= league[B][2]) {    //가지치기
				if(dfs(match, A, B + 1)) return true;
			}
			match[A][0]--;
			match[B][2]--;
			
			//비긴경우
			match[A][1]++;
			match[B][1]++;
			if(match[A][1] <= league[A][1] && match[B][1] <= league[B][1]) {
				if(dfs(match, A, B + 1)) return true;
			}
			match[A][1]--;
			match[B][1]--;
			
			
			//진경우
			match[A][2]++;
			match[B][0]++;
			if(match[A][2] <= league[A][2] && match[B][0] <= league[B][0]) {
				if(dfs(match, A, B + 1)) return true;
			}
			match[A][2]--;
			match[B][0]--;
		}
		return false;
	}
}
