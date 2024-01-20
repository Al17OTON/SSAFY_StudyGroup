import java.util.*;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int n=sc.nextInt();
			int temp=0;
			int mid=n/2+1;
			int answer=0;
			for (int i = 1; i <=n; i++)
			{
				String[] strArr = sc.next().split("");
				int [] numberArr=new int[n];
				for(int j=1;j<=n;j++){
					numberArr[j-1] = Integer.parseInt(strArr[j-1]);
					if(j<=(mid+temp)&&j>=(mid-temp)) answer+=numberArr[j-1];
				}
				if(i<=n/2) temp++;
				else temp--;
			}
			System.out.println("#"+test_case+" "+answer);
		}
	}
}
/*
 n<=49
 구현 문제. O(n^2)
 */
