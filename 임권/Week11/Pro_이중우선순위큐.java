package Week11;

import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        TreeSet<Integer> tree = new TreeSet();
        
        for(String oper : operations) {
            char c = oper.charAt(0);
            int num = Integer.parseInt(oper.substring(2));
            if(c == 'I') tree.add(num);
            else if(c == 'D' && num == 1) {
                if(!tree.isEmpty()) tree.pollLast();
            } else {
                if(!tree.isEmpty()) tree.pollFirst();
            }
        }
        
        if(tree.isEmpty()) return new int[] {0, 0};
        
        return new int[] {tree.last(), tree.first()};
        
        
    }
}