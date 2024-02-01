import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
// import java.util.StringTokenizer;

/* BOJ 5904. Moo 게임 */
public class Problem5904 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input/boj/Input5904.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int mid = 3;
        int len = 3;

        while (N > len) {
            len += len + ++mid;
        }

        System.out.println(getMoo(N, mid, len));

        br.close();
    }

    private static char getMoo(int n, int mid, int len) {
        int left = (len - mid) / 2;

        if (n <= left) {
            return getMoo(n, mid - 1, left);
        } else if (n > left && n <= left + mid) {
            if (n - left == 1) {
                return 'm';
            } else {
                return 'o';
            }
        } else {
            return getMoo(n - left - mid, mid - 1, left);
        }
    }
}

// 메모리 초과
// import java.io.BufferedReader;
// import java.io.FileInputStream;
// import java.io.InputStreamReader;
// // import java.util.StringTokenizer;

// public class Main {
// 	static int N;
// 	static StringBuilder moo = new StringBuilder();

// 	public static void main(String[] args) throws Exception {
// 		System.setIn(new FileInputStream("Input5904.txt"));
// 		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
// 		// StringTokenizer st;
		
// 		N = Integer.parseInt(br.readLine()) - 1;
// 		moo.append("moo");
// 		getMoo(0);
		
// 		System.out.println(moo.charAt(N));
		
// 		// br.close();
// 	}

// 	private static void getMoo(int n) {
// 		if (N < moo.length()) {
// 			return;
// 		}
		
// 		StringBuilder tempMoo = new StringBuilder();
// 		StringBuilder middleMoo = new StringBuilder();
// 		middleMoo.append("m");
		
// 		for (int i = 0; i < n + 3; i++) {
// 			middleMoo.append("o");
// 		}
		
// 		tempMoo.append(moo);
// 		moo.append(middleMoo);
// 		moo.append(tempMoo);
		
// 		getMoo(n + 1);
// 	}
// }

