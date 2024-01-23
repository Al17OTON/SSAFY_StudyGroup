import java.util.*;

public class BOJ_1592_영식이와친구들 {

	public static void main(String[] args)throws Exception {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		int l=sc.nextInt();
		int ballCount[]=new int[n];
		int curr=0;
		int count=0;
		while(ballCount[curr]<=m) {
			ballCount[curr]++;
			if(ballCount[curr]==m)break;
			//공던져야해
			if(ballCount[curr]%2==0) {//반시계: index-=l
				if(curr-l<0) {
					curr=n-(l-curr);
				}else {
					curr-=l;
				}
			}else {//시계: index+=l
				if(curr+l>=n) {
					curr=curr+l-n;
				}else {
					curr+=l;
				}
			}
			count++;
		}
		System.out.println(count);
	}

}
/*
n,m<50

 결과는 하나, 반복적인 계산 후 결과가 무엇인지
 만약 m값이 컸다면 계산을 줄일 점화식이 있나?
 몰라..
 
 void 공던지기(int 받을 애)
 	만약 받은 애가 m번 받았으면 return
 	아니면
 	공던지기를 하는데
 	받을 애는 현재 받은 애의 횟수에 따라 시계, 반시계
 	-> 인덱싱 처리를 해야한다.
 	linkedList?
 	굳이.. 어차피 n 다음은 항상 0이다.
*/