package 전영주.Week03;
import java.util.*;
public class BOJ_5904_Moo게임{

    static int prevLen=3;
    static int curLen=10;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        int n=sc.nextInt();
        getLen(n,3,3);
    }
    static void getLen(int targetLen,int currLen, int middleLen) {
        //middle: o가 k+2개인 수열 길이를 자체로 변수화한다
        int newLen=currLen*2+middleLen+1;
        //아직 재귀 더 돌려야할때
        if(newLen<targetLen) {
            getLen(targetLen,newLen,middleLen+1);
        }
        if(targetLen<=3) {
            switch(targetLen) {
            case 1:
                System.out.println("m");
            break;
            case 2,3:
                System.out.println("o");
            break;
            }
            System.exit(0);
        }
        //currLen<targetLen<newLen인 상태에서 타겟이 어디에 속하는지 확인
        //가운데에 속할 경우.
        if(currLen<targetLen&&targetLen<=currLen+middleLen) {
            switch(targetLen-currLen) {
            case 1:
                System.out.println("m");
            break;
            default:
                System.out.println("o");
            break;
         }
            System.exit(0);
        }
        else {//뒤 s(k-1) 있기 때문에 뒤에 어디 있는지 확인해야함
            getLen(targetLen-(currLen+middleLen),3,3);
        }

    }
}
/*
k= k-1  m+o*k+2 +k-1
n<10^9 아슬아슬 하게 int, 너무 큼. O(logN)으로 하던가 해야함
길이만 저장하기
dp[i]=i번째 수열 길이

while(dp[i]<n) {
    getLength(++i);
}

//i번째 수열에 n이 있음
//i번째 수열은 s(i-1)+m+o*(i+2)+s(i-1)
//이중 어디에 있는지 확인
//n-dp[i-1]을 하면 dp[i]번째에서 n이 몇번째인지 알 수 있음

int specific=n-dp[i-1]; while(dp[i]<specific) { getLength(++i); } //... specific이 dp[i-1]에서 어디에 속하는지 알 수 있음.. //반복
메모리 초과 남 128이므로 빡세긴 함
->배열을 없애고 변수로만 처리
or 이진탐색?
9999 답은 m인데 o 출력
*/