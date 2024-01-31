package 전영주.Week03;
import java.util.Scanner;

public class BOJ_3985_롤케이크 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int l=sc.nextInt();
		int n=sc.nextInt();
		boolean roll[]=new boolean [l+1];//이미 임자가 있는지 없는지 저장 배열
		int expectMax=0;
		int realMax=0;
		int [] answer= {0,0};
		for (int i = 0; i < n; i++) {
			int start=sc.nextInt();
			int end=sc.nextInt();
			int curr=0; //현재 입력 받은 사람의 조각 수 초기화
			for (int j = start; j <= end; j++) {
				if(roll[j])continue;
				roll[j]=true;
				curr++;
			}
			if(expectMax<end-start+1) {
				expectMax=end-start+1;
				answer[0]=i+1;
			}
			if(realMax<curr) {
				realMax=curr;
				answer[1]=i+1;
			}
		}
		System.out.println(answer[0]);
		System.out.println(answer[1]);
		
	}
	
}
