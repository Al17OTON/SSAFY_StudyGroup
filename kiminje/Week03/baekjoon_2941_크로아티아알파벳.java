import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* BOJ 2941. 크로아티아 알파벳 */
public class Problem2941 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input/boj/Input2941.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String inputStr = br.readLine();
        int answer = 0;

        for (int i = inputStr.length() - 1; i >= 0; i--) {
            if (inputStr.charAt(i) == '=') {
                if (inputStr.charAt(i - 1) == 'c' || inputStr.charAt(i - 1) == 's') {
                    i--;
                } else if (i - 2 >= 0 && inputStr.charAt(i - 2) == 'd') {
                    i -= 2;
                } else {
                    i--;
                }

                answer++;
            } else if (inputStr.charAt(i) == '-') {
                i--;
                answer++;
            } else if (inputStr.charAt(i) == 'j') {
                if (i - 1 >= 0 && (inputStr.charAt(i - 1) == 'l' || inputStr.charAt(i - 1) == 'n')) {
                    i--;
                }

                answer++;
            } else {
                answer++;
            }
        }

        System.out.println(answer);

        br.close();
    }
}
