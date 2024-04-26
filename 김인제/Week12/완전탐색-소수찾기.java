import java.util.*;

class Solution {
    static String[] nArr;
    static boolean[] visited;
    static HashSet<Integer> set = new HashSet<>();
    static StringBuilder sb = new StringBuilder();
    
    public int solution(String numbers) {
        nArr = numbers.split("");
        visited = new boolean[nArr.length];
        
        permutation(0);
        
        System.out.println(set);

        int answer = 0;
        
        for (Integer i : set) {
            if (prime(i)) {
                answer++;
            }
        }
        
        return answer;
    }
    
    static void permutation(int depth) {
        if (sb.length() != 0) {
            set.add(Integer.parseInt(sb.toString()));
        }
        
        if (depth == nArr.length) {
            return;
        }
        
        for (int i = 0; i < nArr.length; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            sb.append(nArr[i]);
            permutation(depth + 1);
            visited[i] = false;
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    
    static boolean prime(int n) {
        if (n <= 1) {
            return false;
        }
        
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        
        return true;
    }
}
