package 전영주.Week08;
import java.io.*;
import java.util.*;
public class BOJ_2470_두용액 {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		long[]arr=new long[n];
		StringTokenizer st=new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i]=Long.parseLong(st.nextToken());
		}
		Arrays.sort(arr);
		int start=0,end=n-1;
		int tempS=0,tempE=0;
		long tempAbs=Long.MAX_VALUE;
		while(true) {
			if(start==end) {
				break;
			}
			long abs=Math.abs(arr[start]+arr[end]);
			if(abs<tempAbs) {
				tempAbs=abs;
				tempE=end;
				tempS=start;
			}
			if(arr[start]+arr[end]==0) {
				
				break;
			}else if(arr[start]+arr[end]>0){
				end--;
			}else {
				start++;
			}
		}
		System.out.println(arr[tempS]+" "+arr[tempE]);
	}

}
/*1초 128MB
 * n<십만 k<십억 long 으로 받아야함
 * 특성값이 0에 가장 가까운 용액을 만들 수 있는지. 두 용액으로 오름차순으로 출력
 * 정렬 후 투 포인터
 * 언제 포인터를 옮길 것인가
 * -> 0  -1 비교1
 * 1 -1 비교 2
 * 0 -2 비교 3
 * 합이 작을 수록 좋음
 *
 * 결국 다 돌아야한다. 한번씩은 비교해야함.
 * 
 */