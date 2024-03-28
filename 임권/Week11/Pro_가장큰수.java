package Week11;

import java.util.*;
class Solution {
    class Node implements Comparable<Node> {
        String a;
        public Node(String a) {this.a = a;}
        
        @Override
        public int compareTo(Node o) {
            int len = this.a.length() < o.a.length() ? this.a.length() : o.a.length();
            for(int i = 0; i < len; i++) {
                char A = this.a.charAt(i), B = o.a.charAt(i);
                
                if(A > B) return -1;
                else if(A < B) return 1;
                
            }
            
            int AB = Integer.parseInt(this.a + o.a);
            int BA = Integer.parseInt(o.a + this.a);
            
            return Integer.compare(BA, AB);
        }
    }

    public String solution(int[] numbers) {
        PriorityQueue<Node> pq = new PriorityQueue();
        
        for(int a : numbers) {
            pq.offer(new Node(a + ""));
        }
        
        if(!pq.isEmpty() && pq.peek().a.equals("0")) return "0";
        
        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()) {
            sb.append(pq.poll().a);
        }
        
        return sb.toString();
    }
    
    
}
