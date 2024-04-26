import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* BOJ 2447. 별 찍기 - 10 */
public class Problem2447 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input/boj/Input2447.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringBuilder[] starArr = star(N);

        for (StringBuilder star : starArr) {
            System.out.println(star);
        }

        br.close();
    }

    private static StringBuilder[] star(int n) {
        if (n == 3) {
            StringBuilder[] result = new StringBuilder[3];

            result[0] = new StringBuilder();
            result[0].append("***");
            result[1] = new StringBuilder();
            result[1].append("* *");
            result[2] = new StringBuilder();
            result[2].append("***");

            return result;
        }

        StringBuilder[] result = new StringBuilder[n];
        StringBuilder[] beforeStar = star(n / 3);

        for (int i = 0; i < n; i++) {
            result[i] = new StringBuilder();

            if (i < (n / 3)) {
                for (int j = 0; j < 3; j++) {
                    result[i].append(beforeStar[i % (n / 3)]);
                }
            } else if (i < ((n * 2) / 3)) {
                String blank = "";

                for (int j = 0; j < n / 3; j++) {
                    blank += " ";
                }

                result[i].append(beforeStar[i % (n / 3)]);
                result[i].append(blank);
                result[i].append(beforeStar[i % (n / 3)]);
            } else {
                for (int j = 0; j < 3; j++) {
                    result[i].append(beforeStar[i % (n / 3)]);
                }
            }
        }

        return result;
    }
}
