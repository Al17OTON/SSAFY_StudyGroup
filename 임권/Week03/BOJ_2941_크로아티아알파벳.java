package Week03;

import java.util.Scanner;

public class BOJ_2941_크로아티아알파벳 {

    static String[] arr = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String croatia = sc.next();

        int count = 0;
        
        for(int i = 0; i < croatia.length();) {
            int j;
            for(j = 0; j < arr.length; j++) {
                if(i + arr[j].length() - 1 < croatia.length() && croatia.substring(i, i + arr[j].length()).equals(arr[j])) {    //만약 범위내에 크로아티아 문자가 있다면 
                    i += arr[j].length();   //해당 위치까지 인덱스 이동
                    count++;
                    break;                    
                }
            }
            if(j == arr.length) {   //만약 위 반복문에서 크로아티아 탐색에 실패했다면 i값이 변동되지 않았을 테니 ++한다. 그리고 문자 하나도 카운트한다.
                count++;
                i++;
            }
        }

        System.out.println(count);
    }
}
