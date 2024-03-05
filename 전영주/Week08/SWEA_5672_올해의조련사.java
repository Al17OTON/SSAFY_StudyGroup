package 전영주.Week08;

import java.io.*;
import java.util.*;

public class SWEA_5672_올해의조련사 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br=new  BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		int T=Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			int n=Integer.parseInt(br.readLine());
			char[]bird=new char[n];
			for (int j = 0; j < n; j++) {
				char tmp=br.readLine().charAt(0);
				bird[j]=tmp;
			}
			StringBuilder sb=new StringBuilder();
			int fIdx=0;
			int lIdx=n-1;
			while(sb.toString().length()<n) {
				char front=bird[fIdx];
				char last=bird[lIdx];
				if(fIdx==lIdx) {
					//sb.append(front);
					bw.append(front);
					break;
				}
				if(front>last) {
					//sb.append(last);
					bw.append(last);
					lIdx--;
				}else if(front==last) {
					//sb.append(last);
					bw.append(last);
					int count=1;
					while(bird[fIdx+count]==bird[lIdx-count]&&fIdx+count<lIdx-count)count++;
					if(bird[fIdx+count]>bird[lIdx-count])lIdx--;
					else fIdx++;
				}
				else {
					//sb.append(front);
					bw.append(front);
					fIdx++;
				}
				
			}
			System.out.print("#"+i+" ");
			bw.flush();
			System.out.println();
		}
	}

}
/*
 * 첨엔 그냥 앞 뒤를 비교해가며 넣어줬는데 같을 경우문제가 발생
 * 같을 경우는 그 다음 애를 비교해야함.
 * 
 */