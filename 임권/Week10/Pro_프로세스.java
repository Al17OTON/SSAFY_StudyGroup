package Week10;

import java.util.*;

class Solution {
    
    class Proccess {
        int pri, num;
        Proccess(int a, int b) {
            pri = a;
            num = b;
        }
    }
    
    public int solution(int[] priorities, int location) {
        Queue<Proccess> q = new LinkedList();
        
        for(int i = 0; i < priorities.length; i++) {
            q.add(new Proccess(priorities[i], i));
        }
        
        int idx = priorities.length - 1, count = 1;
        Arrays.sort(priorities);
        
        while(!q.isEmpty()) {
            for(int i = 0; i < q.size(); i++) {
                Proccess pro = q.poll();
                if(priorities[idx] == pro.pri) {
                    if(location == pro.num) return count;
                    count++;
                    idx--;
                } else {
                    q.offer(pro);
                }

            }
        }
        
       
        return count;
    }
    
}