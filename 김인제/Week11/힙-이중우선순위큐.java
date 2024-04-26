import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        TreeSet<Integer> ts = new TreeSet<>();
        
        for (int i = 0; i < operations.length; i++) {
            String[] comm = operations[i].split(" ");
            char c = comm[0].charAt(0);
            int n = Integer.parseInt(comm[1]);
            
            if (c == 'I') {
                ts.add(n);
            } else if (!ts.isEmpty() && n == 1) {
                ts.remove(ts.last());
            } else if (!ts.isEmpty() && n == -1) {
                ts.remove(ts.first());
            }
        }
        
        int[] answer = new int[2];
        
        if (!ts.isEmpty()) {
            answer[1] = ts.first();
            answer[0] = ts.last();
        }
        
        return answer;
    }
}
