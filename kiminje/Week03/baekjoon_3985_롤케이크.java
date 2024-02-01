import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* BOJ 3985. 롤 케이크 */
public class Problem3985 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input/boj/Input3985.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int L = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        int[] exp = new int[2];
        int[][] wantedList = new int[N][2];

        for (int i = 0; i < N; i++) {
            String[] wantedListStr = br.readLine().split(" ");

            wantedList[i][0] = Integer.parseInt(wantedListStr[0]);
            wantedList[i][1] = Integer.parseInt(wantedListStr[1]);

            int tempMax = wantedList[i][1] - wantedList[i][0] + 1;

            if (exp[1] < tempMax) {
                exp[0] = i + 1;
                exp[1] = tempMax;
            }
        }

        boolean[] rollCake = new boolean[L];
        int[] act = new int[2];

        for (int i = 0; i < wantedList.length; i++) {
            int tempMax = 0;

            for (int j = wantedList[i][0] - 1; j < wantedList[i][1] ; j++) {
                if (!rollCake[j]) {
                    tempMax++;
                    rollCake[j] = true;
                }
            }

            if (act[1] < tempMax) {
                act[0] = i + 1;
                act[1] = tempMax;
            }
        }

        System.out.println(exp[0]);
        System.out.println(act[0]);

        br.close();
    }
}

