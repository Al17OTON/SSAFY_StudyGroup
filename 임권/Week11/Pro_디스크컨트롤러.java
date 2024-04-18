package Week11;

import java.util.*;

class Solution {
    
    class Job implements Comparable<Job>{
        int time, len;
        public Job(int t, int l) {
            time = t;
            len = l;
        }
        
        @Override
        public int compareTo(Job o) {
            return Integer.compare(this.len, o.len);
        }
    }
    
    public int solution(int[][] jobs) {
        PriorityQueue<Job> pq = new PriorityQueue();
        ArrayList<Integer>[] line = new ArrayList[1001];
        
        for(int[] job : jobs) {
            if(line[job[0]] == null) line[job[0]] = new ArrayList();
            line[job[0]].add(job[1]);
        }
        
        int sum = 0, cur = 0, cnt = 0, t;
        for(t = 0; t < line.length; t++) {
            if(line[t] != null) {
                //pq.offer(new Job(t, line[t]));
                for(int i = 0; i < line[t].size(); i++) {
                    pq.offer(new Job(t, line[t].get(i)));
                }
            }
            
            if(cur == 0 && !pq.isEmpty()) {
                Job j = pq.poll();
                cur = j.len;
                sum += j.len;
                sum += t - j.time;
                cnt++;
            }
            
            if(cur > 0) cur--;
        }
        
        while(!pq.isEmpty()) {
            if(cur == 0 && !pq.isEmpty()) {
                Job j = pq.poll();
                cur = j.len;
                sum += j.len;
                sum += t - j.time;
                cnt++;
            }
            
            if(cur > 0) cur--;
            t++;
        }
        
        return sum / jobs.length;
    }
}



