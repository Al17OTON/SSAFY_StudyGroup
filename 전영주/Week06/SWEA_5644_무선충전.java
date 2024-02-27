package 전영주.Week06;
import java.util.*;
import java.io.*;
public class SWEA_5644_무선충전 {
    static List<Integer>[][] arr ;//i,j에서 몇번 무선충전이 가능한지
    static BC[] batteryChargeres;
    static int answer,m,a;//총 이동시간, 배터리 개수
    static int[] aMove;
    static int[] bMove;
    static int[]dx=new int[] {0,-1,0,1,0};
    static int[]dy=new int[] {0,0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            
            StringTokenizer st=new StringTokenizer(br.readLine());
            m=Integer.parseInt(st.nextToken());
            a=Integer.parseInt(st.nextToken());//무선충전 개수
            aMove=new int[m];
            bMove=new int[m];
            st=new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                
                aMove[i]=Integer.parseInt(st.nextToken());
            }
            st=new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                
                bMove[i]=Integer.parseInt(st.nextToken());
            }
            arr=new ArrayList[10][10];
            batteryChargeres=new BC[a];
            
            answer=0;
            
            for (int i = 0; i <a; i++) {
                st=new StringTokenizer(br.readLine());
                 int y=Integer.parseInt(st.nextToken())-1;
                 int x=Integer.parseInt(st.nextToken())-1;
                 int c=Integer.parseInt(st.nextToken());
                 int p=Integer.parseInt(st.nextToken());
                 batteryChargeres[i]=(new BC(i,x,y,c,p));
            }
            
            int time=0;
            //시작시간에 얻을 수있는 충전량 계산
            //a는 0,0 b는 9,9
            int aX=0,aY=0;
            int bX=9,bY=9;
            while(time<=m) {
                if(time!=0) {
                    //a,b 움직이기
                    aX+=dx[aMove[time-1]];
                    aY+=dy[aMove[time-1]];
                    bX+=dx[bMove[time-1]];
                    bY+=dy[bMove[time-1]];
                    
                }
                
                List<Integer>aCanConnect=new ArrayList<>();
                List<Integer>bCanConnect=new ArrayList<>();
                
                 //위치에 따라 어느 충전기를 쓸 수 있는지 확인
                 for(BC bc:batteryChargeres) {
                     if(bc.canCharge(aX, aY)&&bc.canCharge(bX, bY)) {
                         aCanConnect.add(bc.index);
                         bCanConnect.add(bc.index);
                     }
                     else if(bc.canCharge(aX, aY)) {
                         aCanConnect.add(bc.index);
                     }
                     else if(bc.canCharge(bX, bY)) {
                         bCanConnect.add(bc.index);
                     }
                 }
                 int currChargeValue=0;//현재 시간에 충전되는 최대값
                 if(aCanConnect.size()>0&&bCanConnect.size()>0) {
                     for(int aI:aCanConnect) {//선택하는 조합
                         for(int bI:bCanConnect) {
                             //a는 aI 충전기 선택
                             //b는 bI 충전기 선택
                             batteryChargeres[aI].connectCount++;
                             batteryChargeres[bI].connectCount++;
                             //계산 함수 ㄱㄱ
                             currChargeValue=Math.max(currChargeValue, calculate());
                             batteryChargeres[aI].connectCount--;
                             batteryChargeres[bI].connectCount--;
                         }
                     }
                 
                 }else if(aCanConnect.size()>0){
                     for(int aI:aCanConnect) {
                        
                             //a는 aI 충전기 선택
                             //b는 bI 충전기 선택
                             batteryChargeres[aI].connectCount++;
                             //계산 함수 ㄱㄱ
                             currChargeValue=Math.max(currChargeValue, calculate());
                             batteryChargeres[aI].connectCount--;                         
                     }
                 }else if(bCanConnect.size()>0) {
                     for(int bI:bCanConnect) {
                         //b는 bI 충전기 선택
                         batteryChargeres[bI].connectCount++;
                         //계산 함수 ㄱㄱ
                         currChargeValue=Math.max(currChargeValue, calculate());
                         batteryChargeres[bI].connectCount--;
                     }
                 
                 }
                 
                 answer+=currChargeValue;
                 time++;
            }
            System.out.println("#"+t+" " +answer);
        }
       
    }
    private static int calculate() {
        int res=0;
        //충전기를 돌면서 사용하고 있는 performace를 더해주면 됨
        //한명이 있든 두명이 있든 어차피 모든 사용자의 충전량이므로 걍 얠 더하면 됨.
        for(BC bc:batteryChargeres) {
            if(bc.connectCount>0) {
            	res+=bc.performance;
                //res+=(bc.performance/bc.connectCount)*bc.connectCount;
            }
        }
        return res;
    }

   static class BC{
       int x;
       int y;
       int coverage;
       int performance;
       int index;
       int connectCount=0;
    public BC(int index,int x, int y, int coverage, int performance) {
        super();
        this.index=index;
        this.x = x;
        this.y = y;
        this.coverage = coverage;
        this.performance = performance;
    }
    public boolean canCharge(int x,int y) {
        int distance=Math.abs(x-this.x)+Math.abs(y-this.y);
        if(distance<=this.coverage)return true;
        else return false;
    }
      
   }
}

/*
 * 지도의 크기는 10으로 고정 
 * 시뮬레이션.
 * 충전기의 위치에 따른 충전 가능 여부를 배열에 저장.
 * 매 초 마다 사용자의 위치에 따라 어떤 충전기를 연결할지 조합
 * 
 */