package 목요빈.Week05;

import java.util.*;

public class BOJ_6987_월드컵 {
	
	static int[] temp;
	static Game[] games;
	static Game[] cases;
	static boolean flag;

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		Scanner sc = new Scanner(System.in);

		// 6개국 경기 횟수: 15회
		for (int i = 0; i < 4; i++) {
			temp = new int[2];
			games = new Game[6];
			cases = new Game[6];
			
			for (int j = 0; j < 6; j++) {
				games[j] = new Game(sc.nextInt(), sc.nextInt(), sc.nextInt());
				cases[j] = new Game(0, 0, 0);
			}

			flag = false;
			dfs(0, 1);
			
			int ans = flag ? 1 : 0;
			sb.append(ans + " ");
		}

		System.out.println(sb);
		sc.close();
	}
	
	static boolean compare() {
		for (int i = 0; i < 6; i++) {
			if(!cases[i].equals(games[i])) return false;
		}
		return true;
	}
	
	static void dfs(int home, int away) {
		// result - 0: win, 1: tie, 2: lose
		if(flag) return;
		
		if(away == 6) {
			home += 1;
			away = home + 1;
		}
		
		if(home == 5) {
			if(compare()) flag = true;
			return;
		}

		cases[home].win += 1;
		cases[away].lose += 1;
		dfs(home, away + 1);
		cases[home].win -= 1;
		cases[away].lose -= 1;
		
		cases[home].tie += 1;
		cases[away].tie += 1;
		dfs(home, away + 1);
		cases[home].tie -= 1;
		cases[away].tie -= 1;
		
		cases[home].lose += 1;
		cases[away].win += 1;
		dfs(home, away + 1);
		cases[home].lose -= 1;
		cases[away].win -= 1;
	}
}
class Game {
	int win, tie, lose;

	public Game(int win, int tie, int lose) {
		super();
		this.win = win;
		this.tie = tie;
		this.lose = lose;
	}

	public boolean equals(Game obj) {
		return this.win == obj.win && this.tie == obj.tie && this.lose == obj.lose;
	}
}

