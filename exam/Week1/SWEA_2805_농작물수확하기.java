import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


//      상단
//        i == 0 ,j = 2
//        i == 1 ,j = 1~3
//        i == 2 ,j = 0~4
//        범위 : n/2+1 = r, range(i,r-i-1:r+i+1)

//      하단
//        i == 3 ,j = 1~3
//        i == 4 ,j = 2
//        범위 : n/2+1 = r, range(i,i-r:n-(i-r))
//

public class SWEA_2805_농작물수확하기 {

    public static void main(String[] args) throws IOException {
        start();
    }

    private static void start() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for(int s = 1; s<=t; s++) {
            int n = Integer.parseInt(br.readLine());
            String line;
            int[][] arr = new int[n][n];
            for (int i = 0; i < n; i++) {
                line = br.readLine();
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
                }
            }
            int ans = getAns(n, arr);
            System.out.printf("#%d %d\n",s,ans);
        }
    }

    private static int getAns(int n, int[][] arr) {
        int r = n/2;
        int ans = 0;
        for(int i = 0; i< n; i++){
            if(i<r+1){
                for(int j =r-i;j<r+i+1;j++){
                    ans += arr[i][j];
                }}else{
                for(int k = i-r; k<n-(i-r); k++){
                    ans += arr[i][k];
                }
            }
        }
        return ans;
    }
}