package problemSolving_homework;

import java.util.*;

public class BOJ_6987_월드컵 {
	static int T=4;
	static int [][]result;
	static int [][]match;
	static int win,lose, tie,tieMatch,answer;
	static boolean flag;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		
		for (int i = 0; i <T ; i++) {
			StringTokenizer st=new StringTokenizer(sc.nextLine());
			win=0;lose=0;tie=0;tieMatch=0;answer=0;
			result=new int[6][3];
			for (int j = 0; j < 6; j++) {
				for (int k = 0; k < 3; k++) {
					result[j][k]=Integer.parseInt(st.nextToken());
				}
				
				win+=result[j][0];
				tie+=result[j][1];
				lose+=result[j][2];
				tieMatch=Math.abs(tieMatch-result[j][1]);
			}
			if(check()) {
				makeMatch();
				flag=false;//가지 끝까지 갔는지 여부
				backTracking(0);
				System.out.print(answer+" ");
			}
			else System.out.print(0+" ");
		}
	}
	//경기 조합만들기
	private static void makeMatch() {
		match=new int[15][2];
		int index=0;
		for (int j = 0; j < 6-1; j++) {
			for (int k = j+1; k < 6; k++) {
				match[index]= new int []{j,k};
				index++;
			}
		}
	}
	private static void backTracking(int matchIndex) {
		//basis part
		if(matchIndex==15) {
			answer=1;
			flag=true;
			return;
		}
		if(flag) {
			return;
		}
		int a=match[matchIndex][0];
		int b=match[matchIndex][1];
		boolean impossible= true;
		//a가 이김
		if(result[a][0]>0&&result[b][2]>0) {
			result[a][0]--;
			result[b][2]--;
			backTracking(matchIndex+1);
			result[a][0]++;
			result[b][2]++;
			impossible=false;
		}
		//비김
		if(result[a][1]>0&&result[b][1]>0) {
			result[a][1]--;
			result[b][1]--;
			backTracking(matchIndex+1);
			result[a][1]++;
			result[b][1]++;
			impossible=false;
		}
		//b가 이김
		if(result[a][2]>0&&result[b][0]>0) {
			result[a][2]--;
			result[b][0]--;
			backTracking(matchIndex+1);
			result[a][2]++;
			result[b][0]++;
			impossible=false;
		}
		
		
	}
	private static boolean check() {
		//승수와 패수가 같은지
		if(win!=lose)return false;
		//무승부 수가 짝수인지
		if(tie%2!=0)return false;
		//무승부 결과가 한 사람에 있지 않은지
		//if(tieMatch>0)return  false;
		//승 무 패 총 합이 30
		if(win+lose+tie!=30)return false;
		//한팀의 경기 횟수가 5인지
		for (int i = 0; i < 6; i++) {
			if(result[i][0]+result[i][1]+result[i][2]!=5) {
				return false;
			}
		}
		return true;
	}
}
/*
 * 가능한 경우를 살펴본다면,,
 * 가능한 결과를 만들고 백트래킹할까,
 * 아니면 가능하지 않는 이유를 찾을까
 * 
 * 몇번 나라가 이겼는진 중요하지 않음, 이 결과가 가능한지 여부만 살펴보면 됨
 * 승과 패의 합이 같아야하고 무는 짝수개여야한다.
 * 승 무 패의 총 합이 30
 * 이게 틀리면 바로 0
 * =>예외 많음
 *
 *완탐 백트래킹을 결국 해야함 3^15
 *
 * 무승부 결과가 한 사람에 있지 않은지 판별하는 if(tieMatch>0)return  false;
 * 이 식을 넣으면 왜 틀리는지 모르겠음
 */
