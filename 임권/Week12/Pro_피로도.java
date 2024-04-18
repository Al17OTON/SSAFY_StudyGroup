package Week12;

class Solution {
    
    public int solution(int k, int[][] dungeons) {
        return permutation(0, k, dungeons, new boolean[dungeons.length]);
    }
    
    int permutation(int depth, int k, int[][] dungeons, boolean[] v) {
        if(depth == v.length) return depth;
        
        int maxDepth = depth;
        for(int i = 0; i < v.length; i++) {
            if(!v[i] && dungeons[i][0] <= k) {
                v[i] = true;
                maxDepth = Math.max(maxDepth, permutation(depth + 1, k - dungeons[i][1], dungeons, v));
                v[i] = false;
            }
        }
        
        return maxDepth;
    }
}