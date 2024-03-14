package Week09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2470_두용액 {
    static int N;
    static int[] fluid;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        fluid = new int[N];

        for(int n = 0; n < N; n++) {
            fluid[n] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(fluid);

        int min = Integer.MAX_VALUE, a = 0, b = 0;

        int front = 0, end = fluid.length - 1;
        while(front < end) {
            int sum = fluid[end] + fluid[front];

            if(min > Math.abs(sum)) {
                min = Math.abs(sum);
                a = front;
                b = end;
            }

            if(sum > 0) {
                end--;
            } else if(sum < 0) {
                front++;
            } else {
                a = front;
                b = end;
                break;
            }
        }

        System.out.println(fluid[a] + " " + fluid[b]);
    }
}
