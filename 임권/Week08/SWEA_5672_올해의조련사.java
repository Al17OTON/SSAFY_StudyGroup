package Week08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class SWEA_5672_올해의조련사 {
 
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
         
        for(int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();
             
            char[] arr = new char[N];
             
            for(int n = 0; n < N; n++) {
                arr[n] = br.readLine().charAt(0);
            }
             
            int front = 0, end = N - 1;
             
            while(front <= end) {
                if(arr[front] < arr[end]) {
                    sb.append(arr[front] + "");
                    front++;
                } else if(arr[front] > arr[end]) {
                    sb.append(arr[end] + "");
                    end--;
                } 
                else if(arr[front] == arr[end]) {
                    int f = front, e = end, fsum = 0, esum = 0;
                    while(fsum == esum && f <= end && e >= front) {
                        fsum += arr[f++];
                        esum += arr[e--];
                    }
                     
                    if(fsum < esum) {
                        //while(front != f) sb.append(arr[front++] + "");
                        sb.append(arr[front++] + "");
                    } else {
                        //while(end != e) sb.append(arr[end--] + "");
                        sb.append(arr[end--] + "");
                    }
                }
            }
             
            System.out.println("#" + t + " " + sb);
        }
    }
 
}
