package Week13;
import java.util.*;

class Solution {
    
    class Node {
        int to;
        Node next;
        public Node(int to, Node next) {
            this.to = to;
            this.next = next;
        }
    }
    class NodeInfo {
        int in, out;
    }
    
    NodeInfo[] info;
    HashSet<Integer> hash;
    Node[] adj;
    boolean[] v;
    
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        info = new NodeInfo[1000001];
        hash = new HashSet();
        adj = new Node[1000001];
        v = new boolean[1000001];
        
        for(int[] edge : edges) {
            adj[edge[0]] = new Node(edge[1], adj[edge[0]]);
            
            if(info[edge[0]] == null) info[edge[0]] = new NodeInfo();
            if(info[edge[1]] == null) info[edge[1]] = new NodeInfo();
            info[edge[0]].out++;
            info[edge[1]].in++;
            
            hash.add(edge[0]);
            hash.add(edge[1]);
        }
        
        ArrayDeque<Integer> eight = new ArrayDeque();
        int center = -1;
        for(int i : hash) {
            if(info[i].out > 1 && info[i].in == 0) center = i;
            else if(info[i].out == 2 && info[i].in >= 2) eight.add(i);
        }
        
        answer[3] = eight.size();
        while(!eight.isEmpty()) checkCircle(eight.poll()); 
        
        for(Node a = adj[center]; a != null; a = a.next) {
            if(v[a.to]) continue;
            v[a.to] = true;
            if(checkCircle(a.to)) answer[1]++;
            else answer[2]++;
        }
        answer[0] = center;
        
        return answer;
    }
    
    boolean checkCircle(int idx) {
        boolean res = false;
        for(Node a = adj[idx]; a != null; a = a.next) {
            if(!v[a.to]) {
                v[a.to] = true;
                if(checkCircle(a.to)) res = true;
            } else res = true;
        }
        return res;
    }
}












