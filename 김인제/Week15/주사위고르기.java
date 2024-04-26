import java.util.*;

class Solution {
    static int N, max;
    static int[] arr1, arr2, answer; 
    static int[][] dice;
    static ArrayList<Integer> list1, list2;
    
    public int[] solution(int[][] dice) {
        this.dice = dice;
        N = dice.length;
        arr1 = new int[N / 2];
        arr2 = new int[N / 2];
        
        getCom(0, 0);
        
        for (int i = 0; i < answer.length; i++) {
            answer[i]++;
        }
        
        return answer;
    }
    
    static void getCom(int depth, int n) {
        if (depth == N / 2) {
            getAnswer();
            
            return;
        }
        
        if (n == N) {
            return;
        }
        
        arr1[depth] = n;
        getCom(depth + 1, n + 1);
        getCom(depth, n + 1);
    }
    
    static void getAnswer() {
        getArr2();

        list1 = new ArrayList<>();
        list2 = new ArrayList<>();

        calMax1(0, 0);
        calMax2(0, 0);

        Collections.sort(list2);
        
        int total = 0;
        
        for (Integer i : list1) {
            int start = 0;
            int end = list1.size();
            
            while (start < end) {
                int mid = (start + end) / 2;
                
                if (i > list2.get(mid)) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }
            
            total += start;
        }
        
        if (total > max) {
            max = total;
            answer = arr1.clone();
        }
    }
    
    static void getArr2() {
        boolean[] check = new boolean[N];
        
        for (int i : arr1) {
            check[i] = true;
        }
        
        int idx = 0;
        
        for (int i = 0; i < N; i++) {
            if (!check[i]) {
                arr2[idx++] = i;
            }
        }
    }
    
    static void calMax1(int depth, int sum) {
        if (depth == N / 2) {
            list1.add(sum);
            
            return;
        }
        
        for (int i = 0; i < 6; i++) {
            calMax1(depth + 1, sum + dice[arr1[depth]][i]);
        }
    }
    
    static void calMax2(int depth, int sum) {
        if (depth == N / 2) {
            list2.add(sum);
            
            return;
        }
        
        for (int i = 0; i < 6; i++) {
            calMax2(depth + 1, sum + dice[arr2[depth]][i]);
        }
    }
}
