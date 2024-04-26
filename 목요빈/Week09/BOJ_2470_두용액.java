import java.io.*;
import java.util.*;

public class Main {
	
	static int N, min;
	static int[] liq;
	
	public static void main(String[] args) throws IOException {
	     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	     StringTokenizer st;
	     
	     N = Integer.parseInt(br.readLine()); // <= 100,000
	     
	     liq = new int[N]; // 산성: 양수, 알칼리성: 음수
	     st = new StringTokenizer(br.readLine());
	     for (int i = 0; i < N; i++) {
  			liq[i] = Integer.parseInt(st.nextToken());
  		}
	     
	     Arrays.sort(liq);
	     int f = 0;
	     int e = 0;
	     int min = Integer.MAX_VALUE; // 절대값 비교
	     
	     int fidx = 0;
	     int eidx = N-1;
	     while(fidx < eidx) {
	    	 int dif = liq[fidx] + liq[eidx];
	    	 if(min > Math.abs(dif)) {
    			 f = fidx;
    			 e = eidx;
    			 min = Math.abs(dif);
    		 }
	    	 
	    	 if(dif > 0) eidx -= 1;
	    	 else if(dif == 0) break;
	    	 else fidx += 1; // liq[fidx]+liq[eidx] < 0
	     }
	     
	     System.out.println(liq[f] + " " + liq[e]);
	     
	}
}
