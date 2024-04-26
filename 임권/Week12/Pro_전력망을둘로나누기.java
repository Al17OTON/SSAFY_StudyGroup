package Week12;

class Solution {
    
    boolean[][] adj;
    int N, min = 999999;
    
    public int solution(int n, int[][] wires) {
        N = n;
        adj = new boolean[n][n];
        
        for(int i = 0; i < wires.length; i++) {
            int from = wires[i][0] - 1;
            int to = wires[i][1] - 1;
            
            adj[from][to] = true;
            adj[to][from] = true;
        }
        dfs(0, -1);
        return min;
    }
    
    int dfs(int node, int pre) {
        int count = 0;
        for(int i = 0; i < N; i++) {
            if(adj[node][i] && i != pre) {
                int res = dfs(i, node);
                min = Math.min(min, Math.abs((N - res) - res));
                count += res;
            }
        }
        return count + 1;
    }
}







