package Week08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4013_특이한자석 {
	
	static class Gear{
		int top;
		boolean[] arr;
		public Gear() {
			top = 0;
			arr = new boolean[8];
		}
		
		void clock() {
			top = top - 1 == -1 ? arr.length - 1 : top - 1;
		}
		void semiclock() {
			top = top + 1 == arr.length ? 0 : top + 1;
		}
		boolean getLeft() {
			return top - 2 >= 0 ? arr[top - 2] : arr[arr.length + top - 2];
		}
		boolean getRight() {
			return top + 2 < arr.length ? arr[top + 2] : arr[top + 2 - arr.length];
		}
		boolean getTop() {
			return arr[top];
		}
	}
	
	static int K;
	static Gear[] gear = new Gear[4];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			K = Integer.parseInt(br.readLine());
			for(int g = 0; g < gear.length; g++) {
				gear[g] = new Gear();
				st = new StringTokenizer(br.readLine());
				
				for(int i = 0; i < 8; i++) {
					if(st.nextToken().charAt(0) == '1') gear[g].arr[i] = true;
				}
			}
			
			for(int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());
				
				rotate(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()));
			}
			
			int sum = 0;
			int point = 1;
			for(int i = 0; i < gear.length; i++) {
				if(gear[i].getTop()) sum += point;
				point *= 2;
			}
			
			System.out.println("#" + t + " " + sum);
		}

	}
	
	static void rotate(int n, int dir) {
		int[] ro = new int[gear.length];
		ro[n] = dir;
		
		for(int i = n - 1; i >= 0; i--) {
			if(gear[i + 1].getLeft() == gear[i].getRight()) break;
			ro[i] = ro[i + 1] == 1 ? -1 : 1;
		}
		
		for(int i = n + 1; i < gear.length; i++) {
			if(gear[i - 1].getRight() == gear[i].getLeft()) break;
			ro[i] = ro[i - 1] == 1 ? -1 : 1;
		}
		
		for(int i = 0; i < ro.length; i++) {
			if(ro[i] == 1) gear[i].clock();
			else if(ro[i] == -1) gear[i].semiclock();
		}
		
	}

}


















