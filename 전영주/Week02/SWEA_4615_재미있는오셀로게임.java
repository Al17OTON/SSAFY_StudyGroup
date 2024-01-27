import java.util.Scanner;
import java.io.FileInputStream;

class Solution{
  	static int n;
    static int arr[][];//검정이면 true
    static int[] dx={1,0,-1,0,1,1,-1,-1};
    static int[] dy={0,1,0,-1,1,-1,1,-1};
    static int bCount,wCount;
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        int T=sc.nextInt();
        for(int t=1;t<T;t++){
            n=sc.nextInt();
            int m=sc.nextInt();
            arr=new int[n+1][n+1];
            bCount=0;wCount=0;
            //초기 배치 만들기
            arr[n/2][n/2]=2;
            arr[n/2+1][n/2+1]=2;
            arr[n/2+1][n/2]=1;
            arr[n/2][n/2+1]=1;
            while(m-->0){
                int x=sc.nextInt();
                int y=sc.nextInt();
                int c=sc.nextInt();
               changeColor(c,x,y);
            }
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(arr[i][j]==1)bCount++;
                    if(arr[i][j]==2)wCount++;
                }
            }
            System.out.println("#"+t+" "+bCount+" "+wCount);
        }
    }

    static void changeColor(int color,int x,int y){
        //같은 색이 나올 때까지 돌기
        //팔방을 다 확인해야함.
        arr[x][y]=color;
        for(int i=0;i<8;i++){
            int num=1;
            int nextX=x+dx[i]*num;
            int nextY=y+dy[i]*num;
            boolean flag=false;
            while(true){
                if(x+dx[i]*num<1||x+dx[i]*num>n||y+dy[i]*num<1||y+dy[i]*num>n) break;
                if(arr[nextX][nextY]==color)break;//같으면 그만
                num++;
                flag=true;
            }
            if(flag){//바꿔줘야 할 경우   
                while(--num>0){
                    arr[x+dx[i]*num][y+dy[i]*num]=color;
                    changeColor(color,x+dx[i]*num,y+dy[i]*num);
                }
            }     
        }
    }
}
/*
단순 구현
보드 길이가 8이 최대 이므로 끽해야 64번
돌을 놓을 수 이쓴 경우: 자신의 돌과 자신의 돌 사이에 상대편
팔방 탐색 후가로 세로 대각선에 다른 색 돌이 있는 지 확인
바꿔진 돌에 의해서도 상대편의 돌을 없앨 수 있음

개수를 마지막에 for문으로 count할까
일일히 counting 할까

의문점1: 내 돌 사이에 여러 돌을 다 바꿀 수 있는가-> 예제를 통해 가능함 확인
의문점2: 만약 흰 검 빈칸 흰 같은 중간에 빈칸이 있어도 바꿀 수 있나
-> 자신이 놓을 돌과 자신의 돌 사이에 상대편의 돌로 채워져 있는게 아니라 돌이 있는 경우이므로 가능하지 않나?
문제가 너무 불친절함.. 

재귀로
*/
