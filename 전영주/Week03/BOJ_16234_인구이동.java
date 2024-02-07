package 전영주.Week03;
import java.io.*;
import java.util.*;

public class BOJ_16234_인구이동 {
    static int [][] arr;
    static int [][] visited;// 0이면 visited false, 0< groupIndex;
    static int [][] groupAvgInfo;//그룹에 몇명, 합계가 몇인지
    static int groupIndex;
    static int n,l,r;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        l=Integer.parseInt(st.nextToken());
        r=Integer.parseInt(st.nextToken());
        
        arr=new int [n+1][n+1];
        
        for (int i = 1; i <= n; i++) {
            st=new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(checkMove());
    }
    static int checkMove() {
        int answer=0;
        while(true) {
            //변수 초기화
            int isMove=0;//인구 이동이 일어났는지 확인하는 int형
            visited=new int[n+1][n+1];
            groupAvgInfo=new int[n*n+1][2];
            groupIndex=1;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if(visited[i][j]==0) {
                        //만약 한번이라도 bfs에서 연합이 이루어졌다면 isMove는 1이된다.
                        isMove=Math.max(bfs(new Pos(i,j)),isMove);
                    }
                }
            }
            if(isMove==1) {
                answer++;
                //연합 확인 후 이제 인구 이동할 차례
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= n; j++) {
                        int group=visited[i][j];
                        arr[i][j]=groupAvgInfo[group][1]/groupAvgInfo[group][0];
                    }
                }
            }else break;
            
        }
        return answer;
    }
    static int bfs(Pos a) {
        int dx[]= {1,0,-1,0};//아래 오른쪽만 비교하면 된다.
        int dy[]= {0,1,0,-1};
        Queue <Pos>q=new LinkedList<>();
        q.add(a);
        visited[a.x][a.y]=groupIndex;
        int count=1;
        while(!q.isEmpty()) {
            Pos curr=q.poll();
            //합계 계산
            groupAvgInfo[groupIndex][1]+=arr[curr.x][curr.y];
            for (int d = 0; d < 4; d++) {
                int nX=curr.x+dx[d];
                int nY=curr.y+dy[d];
                if(nX<1||nX>=n+1||nY<1||nY>=n+1)continue;
                if(visited[nX][nY]!=0)continue;
                int margin=Math.abs(arr[curr.x][curr.y]-arr[nX][nY]);
                if(l<=margin&&margin<=r){//국경선 열림
                    q.add(new Pos(nX,nY));
                    count++;//연합 국가가 몇개인지
                    visited[nX][nY]=groupIndex; 
                }
            }
        }
        groupAvgInfo[groupIndex][0]=count;
        
        groupIndex++;
        if(count>1)return 1;
        else return 0;
    }
    static class Pos{
        int x;
        int y;
        public Pos(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }
        public boolean isSame(Pos a) {
            if(a.x==this.x&&a.y==this.y)return true;
            return false;    
        }
    }
}
/*
 * 인구 이동이 없을때까지-> while?
 * n<50 구현? 반복? bfs
 * l~r
 * 연합=> 유니온 파인드?
 * 그럼 bfs가 아니라 전체 배열을 순회
 * 1. n^2만큼 bfs
 * 2. n^2만큼 개수 확인

 */