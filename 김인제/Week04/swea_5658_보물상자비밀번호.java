import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/* SWEA 5658. [모의 SW 역량테스트] 보물상자 비밀번호 */
public class Problem5658 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input/swea/Input5658.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N, K, idx;
        String temp;
        StringBuilder sb;
        Integer[] answer;
        String[] inputStr;
        Deque<String> number1 = new LinkedList<>();
        Deque<String> number2 = new LinkedList<>();
        Deque<String> number3 = new LinkedList<>();
        Deque<String> number4 = new LinkedList<>();
        HashSet<String> numbers = new HashSet<>();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc < T + 1; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            inputStr = br.readLine().split("");
            number1.clear();
            number2.clear();
            number3.clear();
            number4.clear();
            numbers.clear();

            for (int i = 0; i < N; i++) {
                if (i < N / 4) {
                    number1.offer(inputStr[i]);
                } else if (i >= N / 4 && i < N / 2) {
                    number2.offer(inputStr[i]);
                } else if (i >= N / 2 && i < (N / 4) * 3) {
                    number3.offer(inputStr[i]);
                } else {
                    number4.offer(inputStr[i]);
                }
            }

            for (int i = 0; i < N / 4; i++) {
                number1.offerFirst(number4.pollLast());
                number2.offerFirst(number1.pollLast());
                number3.offerFirst(number2.pollLast());
                number4.offerFirst(number3.pollLast());

                for (int j = 0; j < 4; j++) {
                    sb = new StringBuilder();

                    if (j == 0) {
                        for (int k = 0; k < N / 4; k++) {
                            temp = number1.poll();
                            sb.append(temp);
                            number1.offer(temp);
                        }
                    } else if (j == 1) {
                        for (int k = 0; k < N / 4; k++) {
                            temp = number2.poll();
                            sb.append(temp);
                            number2.offer(temp);
                        }
                    } else if (j == 2) {
                        for (int k = 0; k < N / 4; k++) {
                            temp = number3.poll();
                            sb.append(temp);
                            number3.offer(temp);
                        }
                    } else {
                        for (int k = 0; k < N / 4; k++) {
                            temp = number4.poll();
                            sb.append(temp);
                            number4.offer(temp);
                        }
                    }

                    numbers.add(sb.toString());
                }
            }

            idx = 0;
            answer = new Integer[numbers.size()];

            for (String str : numbers) {
                answer[idx++] = Integer.parseInt(str, 16);
            }

            Arrays.sort(answer, Collections.reverseOrder());

            System.out.println("#"+ tc + " " + answer[K - 1]);
        }

        br.close();
    }
}
