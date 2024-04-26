package 목요빈.Week08;
import java.io.*;

public class SWEA_5672_올해의조련사 {

	static int N;
	static char[] name;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine().trim()); // <= 2000
			name = new char[N];
			for (int i = 0; i < N; i++) {
				name[i] = br.readLine().trim().charAt(0);
			}
			
			int f = 0;
			int b = N-1;
			String str = "";
			while(str.length() < N) {
				if(name[f] < name[b]) str += name[f++];
				else if(name[b] < name[f]) str += name[b--];
				else {
					int temp_f = f;
					int temp_b = b;
					boolean flag = false;
					while(temp_f < temp_b) {
						if(name[temp_f+1] < name[temp_b-1]) {
							str += name[f++];
							flag = true;
							break;
						}
						else if(name[temp_b-1] < name[temp_f+1]) {
							str += name[b--];
							flag = true;
							break;
						}else {
							temp_f += 1;
							temp_b -= 1;
						}
					}
					if(!flag) str += name[b--];
				}
			}
			
			System.out.printf("#%d %s\n", t, str);
		}	
	}
}
