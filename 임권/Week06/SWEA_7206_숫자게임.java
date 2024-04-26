package Week06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


//dfs 식을 만드는게 생각보다 어려웠다.
//거기다 시간초과나서 백트래킹까지 해야했다.

public class SWEA_7206_숫자게임 {

    //그냥하면 시간초과난다. 
    //아래 배열로 방문 처리를 하면서 낮은 턴수들인 경우면 가지치기를 한다.
    static int[] v;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            //수의 최대값은 99999인데 9 * 9999 나 99 * 999 는 100000를 넘지 못한다.
            v = new int[100000];
            System.out.println("#" + t + " " + dfs(Integer.parseInt(br.readLine()), 0));
        }
    }

    //주어진 수가 10보다 크다면 자를 수 있는 모든 경우의 수를 시도한다.
    static int dfs(int num, int depth) {
        if(num < 10) {
            return depth;
        }

        String tmp = num + "";
        int max = 0;


        //길이를 통해 자를 수 있는 모든 경우의 수를 부른다.
        for(int i = 1; i < tmp.length(); i++) {
            max = Math.max(max, splitNum(num, depth + 1, new boolean[tmp.length() - 1], 0, 0, i));
        }
        
        return max;
    }

    //주어진 값을 가지고 숫자를 자르고 잘린 수의 곱을 구한다.
    static int splitNum(int num, int depth, boolean[] split, int cnt, int idx, int need) {
        if(cnt == need) {
            String tmp = num + "";
            int mul = 1;
            int j = 0;
            for(int i = 0; i < split.length; i++) {
                if(split[i]) {
                    mul *= Integer.parseInt(tmp.substring(j, i + 1));
                    j = i + 1;
                }
            }
            mul *= Integer.parseInt(tmp.substring(j));

            //이전에 같은 숫자를 방문했다면 그때 방문 깊이와 현재 깊이를 비교하여 더 깊은 방문이 아니라면 가지치기한다.
            if(v[mul] > depth) return 0;
            v[mul] = depth; //최신 깊이로 갱신

            return dfs(mul, depth);
        }

        if(idx == split.length) return 0;
        
        int max = 0;

        //해당 지점을 자르거나 안자르거나 한다.
        split[idx] = true;
        max = Math.max(max, splitNum(num, depth, split, cnt + 1, idx + 1, need));

        split[idx] = false;
        max = Math.max(max, splitNum(num, depth, split, cnt, idx + 1, need));

        return max;
    }
}
