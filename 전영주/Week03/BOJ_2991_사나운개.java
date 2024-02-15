package 전영주.Week03;
import java.util.*;

public class BOJ_2991_사나운개 {
    static int answer[]= {0,0,0};

    static void checkTime(int attack,int rest,int arriveTime,int personIndex) {
        int period=attack+rest;
        if(arriveTime%period<=attack&&arriveTime%period!=0) {
            answer[personIndex]++;
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int attackA=sc.nextInt();
        int restA=sc.nextInt();
        int attackB=sc.nextInt();
        int restB=sc.nextInt();
        int p=sc.nextInt();
        int m=sc.nextInt();
        int n=sc.nextInt();

        checkTime(attackA,restA,p,0);
        checkTime(attackB,restB,p,0);
        checkTime(attackA,restA,m,1);
        checkTime(attackB,restB,m,1);
        checkTime(attackA,restA,n,2);
        checkTime(attackB,restB,n,2);

        for(int a:answer) {
            System.out.println(a);
        }
    }
}
/*
개 몇마리에게 공격받는지
시간에 따른
period: 공격 시간+ 쉬는 시간
나누기 해서 나머지에 따라 어느 시간에 속할때 도착하는 지 계산 가능
*/