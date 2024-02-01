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
