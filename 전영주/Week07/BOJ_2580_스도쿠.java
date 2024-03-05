package 전영주.Week07;

import java.util.*;
import java.io.*;

public class BOJ_2580_스도쿠 {
	static int[][]map=new int[9][9];
	static List<int[]>space=new ArrayList<>();
	static boolean[][][]visited=new boolean[3][10][10];//0 가로 1 세로 2 네모
	static boolean answerFlag=false;
	public static void main(String[] args) throws Exception {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 9; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int j = 0; j <9; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==0)space.add(new int[] {i,j});
				else {
					visited[0][i][map[i][j]]=true;//가로
					visited[1][j][map[i][j]]=true;//세로
					visited[2][j/3+i/3*3][map[i][j]]=true;//네모
				}
			}
		}
		dfs(0);
	}
	private static void dfs(int index) {
		//basis part
		if(index==space.size()) {
			//answer
			answerFlag=true;
			print(map);
			return;
		}
		//inducive part
		int[]curr=space.get(index);
		
		for (int i=1; i <= 9; i++) {
 			map[curr[0]][curr[1]]=i;
  			if(checkHorizontal(curr[0])&&checkVertical(curr[1])&&checkSquare(curr[0], curr[1])) {
				visited[0][curr[0]][i]=true;//가로
				visited[1][curr[1]][i]=true;//세로
				visited[2][curr[1]/3+curr[0]/3*3][i]=true;//네모
				dfs(index+1);
				if(answerFlag)return;
				visited[0][curr[0]][i]=false;//가로
				visited[1][curr[1]][i]=false;//세로
				visited[2][curr[1]/3+curr[0]/3*3][i]=false;//네모
			}
  			//원복
			map[curr[0]][curr[1]]=0;
		}
	}
	
	private static boolean checkVertical(int c) {
		boolean []temp=new boolean[10];
		for (int i = 0; i < 9; i++) {
			if(map[i][c]==0)continue;
			if( temp[map[i][c]])return false;
			else temp[map[i][c]]=true;
			
		}
		return true;
	}
	
	private static boolean checkHorizontal(int r) {
		boolean []temp=new boolean[10];
		for (int i = 0; i < 9; i++) {
			if(map[r][i]==0)continue;
			if( temp[map[r][i]])return false;
			else temp[map[r][i]]=true;
			
		}
		return true;
	}
	
	private static boolean checkSquare(int r,int c) {
		boolean []temp=new boolean[10];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(map[r/3*3+i][c/3*3+j]==0)continue;
				if( temp[map[r/3*3+i][c/3*3+j]])return false;
				else temp[map[r/3*3+i][c/3*3+j]]=true;
			}

		}
		return true;
	}
}
