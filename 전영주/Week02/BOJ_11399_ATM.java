package 전영주.Week02;
import java.util.*;

public class BOJ_11399_ATM {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int answer=0;
		int waitSum=0;
		Integer arr[]=new Integer[n];
		for (int i = 0; i < n; i++) {
			arr[i]=(Integer)(sc.nextInt());	
		}
		Arrays.sort(arr);
		for (int i = 0; i < n; i++) {
			waitSum+=arr[i];
			answer+=waitSum;
		}
		System.out.println(answer);
	}

}
