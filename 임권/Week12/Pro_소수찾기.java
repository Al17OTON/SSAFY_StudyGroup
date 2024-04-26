package Week12;

class Solution {
    String num;
    boolean[] v, use;
    int count = 0;
    
    public int solution(String numbers) {
        num = numbers;
        use = new boolean[num.length()];
        v = new boolean[1000000000];
        
        permutation(0, 0);
        
        return count;
    }
    
    void permutation(int n, int depth) {
        if(check(n) && !v[n]) {
            v[n] = true;
            count++;
        }
        
        if(depth == num.length()) return;
        
        for(int i = 0; i < use.length; i++) {
            if(!use[i]) {
                use[i] = true;
                permutation((n * 10) + (num.charAt(i) - '0'), depth + 1);
                use[i] = false;
            }
        }
    }
    
    boolean check(int n) {
        if(n == 0 || n == 1) return false;
        
        for(int i = 2; i <= n / 2; i++) {
            if(n % i == 0) return false;
        }
        return true;
    }
}