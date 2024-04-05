import java.util.*;

class Solution {
    static int N;
    static ArrayList<Integer>[] adjList;
    
    public int solution(int n, int[][] wires) {
        N = n;
        adjList = new ArrayList[n + 1];
        
        for (int i = 1; i < n + 1; i++) {
            adjList[i] = new ArrayList<>();
        }
        
        for (int[] wire : wires) {
            adjList[wire[0]].add(wire[1]);
            adjList[wire[1]].add(wire[0]);
        }
        
        int answer = Integer.MAX_VALUE;
        
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < adjList[i].size(); j++) {
                int a = i;
                int b = adjList[i].get(j);
                
                int ac = cntWire(a, b);
                int bc = cntWire(b, a);
                
                answer = Math.min(answer, Math.abs(ac - bc));
            }
        }
        
        
        return answer;
    }
    
    static int cntWire(int a, int b) {
        int result = 0;
        boolean[] visited = new boolean[N + 1];
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        
        visited[a] = true;
        visited[b] = true;
        dq.offer(a);
        
        while (!dq.isEmpty()) {
            int p = dq.poll();
            
            for (int i = 0; i < adjList[p].size(); i++) {
                int next = adjList[p].get(i);
                
                if (visited[next]) {
                    continue;
                }
                
                result++;
                visited[next] = true;
                dq.offer(next);
            }
        }
        
        return result;
    }
}
