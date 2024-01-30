package Week03;

import java.util.Scanner;

class Pair {    
    int idx, count;

    public void compare(int i, int count) { //케이크를 가장 많이 가진 인덱스 저장
        if(count > this.count) {
            idx = i;
            this.count = count;
        }
    }
}

public class BOJ_3985_롤케이크 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Pair maxExpect = new Pair();    //가장 많은 케이크를 받을 것으로 예상되는 사람
        Pair maxResult = new Pair();    //실제로 가장 많은 케이크를 받은 사람.

        int L = sc.nextInt();
        int N = sc.nextInt();

        boolean[] cakes = new boolean[L + 1];   //가져간 케이크 기록
        
        for(int n = 1; n <= N; n++){
            int p = sc.nextInt();
            int k = sc.nextInt();

            maxExpect.compare(n, k - p);    //가장 많이 케이크를 가지게 될것이라고 예상되는 사람 찾기, 이때 페어에 저장되는 count는 실제 케이크 갯수는 아니지만 단순 비교용이므로 무시

            //케이크 배열을 통해 실제 가져간 케이크 수 체크
            int count = 0;
            for(int i = p; i <= k; i++) {
                if(!cakes[i]) {
                    count++;
                    cakes[i] = true;
                }
            }

            maxResult.compare(n, count);    //실제로 가장 많은 케이크를 가진 사람
        }

        System.out.println(maxExpect.idx);
        System.out.println(maxResult.idx);
    }
}
