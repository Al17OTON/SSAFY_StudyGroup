import java.io.BufferedReader;
// import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("Input6109.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
         
        boolean flag;
        int N, nj;
        String dirStr;
        int[][] board;
         
        int T = Integer.parseInt(br.readLine());
         
        for (int tc = 1; tc < T + 1; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            dirStr = st.nextToken();
            board = new int[N][N];
             
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                 
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
             
            if (dirStr.equals("up")) {
                board = rot(1, N, board);
            } else if (dirStr.equals("right")) {
                board = rot(2, N, board);
            } else if (dirStr.equals("down")) {
                board = rot(3, N, board);
            }
 
             
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    flag = false;
                    nj = j;
                     
                    if (board[i][j] == 0) {
                        flag = true;
                    }
                     
                    while (++nj < N) {
                        if (board[i][nj] == 0) {
                            continue;
                        } else if (board[i][j] == board[i][nj]) {
                            board[i][j] *= 2;
                            board[i][nj] = 0;
                            break;
                        } else if (flag){
                            board[i][j] = board[i][nj];
                            board[i][nj] = 0;
                            j--;
                            break;
                        } else {
                            break;
                        }
                    }
                }
            }
             
 
             
            if (dirStr.equals("up")) {
                board = rot(3, N, board);
            } else if (dirStr.equals("right")) {
                board = rot(2, N, board);
            } else if (dirStr.equals("down")) {
                board = rot(1, N, board);
            }
 
             
            System.out.println("#" + tc);
             
            for(int[] x : board) {
                for (int xy : x) {
                    System.out.print(xy + " ");
                }
                 
                System.out.println();
            }
             
        }
     
        // br.close();
    }
     
    public static int[][] rot(int cnt, int N, int[][] board) {
        int[][] tempBoard;
         
        for (int c = 0; c < cnt; c++) {
            tempBoard = new int[N][N];
             
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    tempBoard[i][j] = board[j][N - i - 1];
                }
            }
             
            board = tempBoard;
        }
         
        return board;
    }
}
