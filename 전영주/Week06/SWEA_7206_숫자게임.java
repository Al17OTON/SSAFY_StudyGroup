package 전영주.Week06;

import java.util.*;
import java.io.*;
public class SWEA_7206_숫자게임 {
    static int answer=0;;
    static int[]countDp;// i번째에서 몇번 나눠질 수 있는지. 한자리는 무조건 0일것.
    public static void main(String[] args) throws Exception {
        BufferedReader    br=new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            String n=br.readLine();
            answer=0;
            countDp=new int[Integer.parseInt(n)+1];
            getAnswer(n);
            answer=countDp[Integer.parseInt(n)];
            System.out.println("#"+t+" " +answer);
        }
    }
    private static void getAnswer(String n) {
        recursive(n);
    }
    
    private static int recursive(String num) {
        //backtracking
        //만약 이미 이 수의 최대 쪼개기 값을 구했다면 넘어가기
        if(countDp[Integer.parseInt(num)]!=0) {
            return Integer.parseInt(num);
        }
        //만약 길이가 1이면 다 쪼갠 것
        if(num.length()==1) {
            countDp[Integer.parseInt(num)]=0;
            return Integer.parseInt(num);
        }
        for (int i = 1; i < num.length(); i++) {
            //i번째 인덱스에서 두개로 쪼개기.
            //dp[i]=지금 값, 쪼갰을 때 dp값+1의 맥스값
            countDp[Integer.parseInt(num)]=Math.max(countDp[Integer.parseInt(num)],countDp[recursive(Integer.toString(splitNum(num, i)))]+1);
            for (int j = i+1; j < num.length(); j++) {
                //세개로 쪼갠 경우.
                countDp[Integer.parseInt(num)]=Math.max(countDp[Integer.parseInt(num)],countDp[recursive(Integer.toString(splitNum3(num, i,j)))]+1);
            }
        }
        return Integer.parseInt(num);
    }
    private static int splitNum3(String num,int index1,int index2) {
        return Integer.parseInt(num.substring(0,index1))*Integer.parseInt(num.substring(index1,index2))*Integer.parseInt(num.substring(index2));
    }
    private static int splitNum(String num,int index) {
        return Integer.parseInt(num.substring(0,index))*Integer.parseInt(num.substring(index));
    }
}
/*
 * n< 99_999
 * 1. 시작수 스트링으로 쪼개기 2~n-1개까지 가능
 * 2. 쪼새진 수를 모두 곱함
 * 3.10이상이면 반복
 * 4. 최대 턴수는?
 * 그리디로 가능한가?
 * 백트래킹? 메모라이징
 * 나눠보고 곱이 가장 큰애를 선택.
 * 근데 많이 쪼갤수록 수가 더 작아짐으로 한번 쪼개는것만 고려하면 되지 않을까?
 * 2개로 쪼개는거 경우의 수 + 3개로 쪼개는 경우의 수 그 이상은 필요 없음
 * 
 * 시간 초과로 count 메모라이제이션 추가.
 * 
 */