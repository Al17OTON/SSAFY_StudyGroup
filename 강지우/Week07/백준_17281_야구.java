//package jiu;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int [][] arr;
    static int n,ans = 0;
    static int[] order,user = {1,2,3,4,5,6,7,8},sel = new int[user.length];
    static boolean[] v = new boolean[user.length];
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("src/jiu/Main.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][9];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        rc(0);

//        game(test);
        System.out.println(ans);
    }

    // 2번부터 9번까지의 타순을 순열로 구함
    private static void rc(int k) {
        if(k == sel.length) {
            game();
            return;
        }

        for (int i = 0; i < user.length; i++) {
            if(!v[i]) {
                v[i] = true;
                sel[k] = user[i];
                rc(k+1);
                v[i] = false;
            }
        }
    }

    private static void game() {
        getOrder();
//        System.out.println(Arrays.toString(order));
        //[8, 7, 6, 0, 5, 4, 3, 2, 1]
        int turn = 0;
        int score = 0;

        for (int i = 0; i < arr.length; i++) {
            int out = 0;
            int[] inning = arr[i];

            int sum = 0;
            int cnt = 0;
            while (out < 3) {
                int user = order[turn++];      // 한 이닝에 가능한 숫자들을 턴을 넘겨가며 0이 아닐때만 체크
                turn %= 9;
                int res = inning[user];
                if (res == 0) {
                    out++;
                } else {
                    if (res == 4) {     // 홈런일때 값 계산후 sum 값 0으로 초기화
                        cnt += cal(sum, true);
                        sum = 0;
                    } else {
                        sum = sum << res | 1<<(res-1);      // 현재 진출을 res 만큼 이동 후 res 값 or ex) 001 3루 1000 + 100 = 1100
                        if (sum >= 8) {     // 1000 -> 점수 획득 오른쪽 3자리를 제외한 1의 수가 획득점수
                            cnt += cal(sum, false);
                            sum = sum & 0b111;      // 점수 계산 뒤 오른쪽 3자리만 남김  0b -> 2진수 표현
//                            System.out.println(sum);
                        }
                    }
                }
            }
            score += cnt;
        }
        ans = Math.max(ans, score);
    }

    private static int cal(int sum, boolean type) {
        if(type){
            return Integer.bitCount(sum & 0b111) + 1;     // 오른쪽 3자리와 sum & 하여 1개수 리턴
        }
        return Integer.bitCount(sum & -1 << 3);     // -1을 왼쪽을 3비트 << 오른쪽 3자리 0으로 -> 왼쪽 비트 1의 개수 리턴
    }


    //  1번(idx = 0)번 타자를 4번 idx 에 추가해준뒤 9명에 대한 타순계산
    private static void getOrder() {
        order = new int[9];
        int idx=0;
        for(int i=0 ; i< 9 ; i++) {
            if(i!=3) {
                order[i] = sel[idx++];
            }
        }
//        System.out.println(Arrays.toString(order));
    }
}