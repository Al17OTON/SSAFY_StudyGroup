package 전영주.Week05;

import java.util.*;
import java.io.*;
public class SWEA_4366_정식이의은행업무 {
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++)
        {
            String binary=br.readLine();
            String ternary=br.readLine();
            System.out.println("#"+t+" "+getAnswer(binary,ternary));
        }
    }
    private static int getAnswer(String binary, String ternary) {
        List<Integer> bi=makeOneDiff(binary, 2);
        List<Integer> ter=makeOneDiff(ternary, 3);
        for(Integer i:bi) {
            if(ter.contains(i))return i;
        }
        return 0;
    }
    private static List<Integer> makeOneDiff(String origin,int num) {
        List<Integer> result=new ArrayList<>();
        for (int i = 0; i < origin.length(); i++) {
            for (int j = 0; j < num; j++) {
                StringBuilder sb=new StringBuilder(origin);
                sb.deleteCharAt(i);
                sb.insert(i,j);
                if(!sb.toString().equals(origin))result.add(Integer.parseInt(sb.toString(), num));
            }
        }
        return result;
    }
}
/*
 * 정식이는 10진수는 까먹고 2진수 3진수는 기억하는 이상한 아이
 * 근데 각각 한 자리는 잘못 기억하는 더 이상한 아이
 * 근데 지 할일 남에게 맡기는 나쁜 아이
 * 추측할 수 없는 경우 없다-> 경우의 수 하나
 * 2진수에서 하나씩 바꿔본 경우,3진수에서 하나씩 바꿔본 경우 의 교집합 하나
 */