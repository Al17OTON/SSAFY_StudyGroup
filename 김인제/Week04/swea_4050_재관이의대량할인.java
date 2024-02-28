import java.io.BufferedReader;
// import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("input/Input4050.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc < T + 1; tc++) {
			int N = Integer.parseInt(br.readLine());
			PriorityQueue<Integer> clothes = new PriorityQueue<>((o1, o2) -> o2 - o1);
			
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < N; i++) {
				clothes.offer(Integer.parseInt(st.nextToken()));
			}
			
			int answer = 0, cnt = 0;
			
			while (!clothes.isEmpty()) {
				int n = clothes.poll();
				
				if (++cnt % 3 != 0) {
					answer += n;
				}
			}
			
			System.out.println("#" + tc + " " + answer);
		}
	
		// br.close();
	}
}
