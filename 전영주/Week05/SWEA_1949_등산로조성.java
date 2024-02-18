package 전영주.Week05;

import java.io.*;
import java.util.*;

public class SWEA_1949_등산로조성 {
    static int[][] arr;// 입력 배열
    static boolean[][] visited;//방문여부 체크 배열
    static int n,k,answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++)
        {
            StringTokenizer st=new StringTokenizer(br.readLine());
            n=Integer.parseInt(st.nextToken());
            k=Integer.parseInt(st.nextToken());
           
            arr=new int [n][n];
            
            answer=0;
            List<int[]>lst=new ArrayList<>();
            int max=0;
            for (int i = 0; i < n; i++) {
                st=new StringTokenizer(br.readLine());
                for (int j =0; j < n; j++) {
                    arr[i][j]=Integer.parseInt(st.nextToken());
                    //봉우리 입력받기
                    if(arr[i][j]>max) {
                        max=arr[i][j];
                        lst.clear();
                        lst.add(new int[] {i,j});
                    }else if(arr[i][j]==max){
                        lst.add(new int[] {i,j});
                    }
                }
            }
            //등산로 시작점은 배열에서 값이 큰==높은 봉우리이다
            lst.forEach(e->{
                visited=new boolean[n][n];
                visited[e[0]][e[1]]=true;
                dfs(e[0],e[1],true,1);
            });
            System.out.println("#"+t+" "+answer);
        }
    }
    
    static void dfs(int x,int y,boolean canChange,int count) {
        
        int dx[]= {1,0,-1,0};
        int dy[]= {0,1,0,-1};
        answer=Math.max(answer, count);
        for (int i = 0; i < 4; i++) {
            int nX=x+dx[i];
            int nY=y+dy[i];
            if(nX<0||nX>=n||nY<0||nY>=n)continue;
            if(visited[nX][nY])continue;
            if(arr[x][y]<=arr[nX][nY]-k)continue;//다음 높이가 현재+k보다 작거나 같으면 깎아도 갈 수 없음
            if(arr[x][y]<=arr[nX][nY]) {
                if(canChange==false)continue;//이미 깎은 경우는 못감
                int temp=arr[nX][nY];
                arr[nX][nY]=arr[x][y]-1;//항상 현재 -1가 되게 깎는게 최적의 경우임
                visited[nX][nY]=true;
                dfs(nX,nY,false,count+1);
                //안가는 경우
                arr[nX][nY]=temp;
                visited[nX][nY]=false;
            }else {
                //안깎고 가기
                visited[nX][nY]=true;
                dfs(nX,nY,canChange,count+1);
                //안가는 경우
                visited[nX][nY]=false;
            }
        }
    }
}
/* 
 * n<8 k<5 -> 완탐
 * 등산로는 높이가 높아지는 방향으로 가로 세로 연결
 * 딱 한곳 k만큼 깎을 수 있다.
 * 가장 긴 등산로의 길이는?
 * dfs 재귀
 */