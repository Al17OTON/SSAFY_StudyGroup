import java.io.*;
import java.util.*;

public class Main {

	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // <= 50
		
		List<Integer> plus = new ArrayList<>();
		List<Integer> minus = new ArrayList<>();		
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(br.readLine());
			if(n > 0) plus.add(n);
			else minus.add(n);
		}
		
		Collections.sort(plus, Comparator.reverseOrder());
		Collections.sort(minus);
		
		int sum = 0;
		int i = 0;
		while(i < plus.size()) {
			if(i+1 < plus.size()) {
				sum += Math.max(plus.get(i) * plus.get(i+1), plus.get(i) + plus.get(i+1));
				i += 2;
			}else {
				sum += plus.get(i++);
			}
		}
		
		i = 0;
		while(i < minus.size()) {
			if(i+1 < minus.size()) {
				sum += minus.get(i) * minus.get(i+1);
				i += 2;
			}else {
				sum += minus.get(i++);
			}
		}
		
		System.out.println(sum);
	}
}
