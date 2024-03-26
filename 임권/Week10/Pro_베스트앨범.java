package Week10;

import java.util.*;
class Solution {
    
    class Music implements Comparable<Music> {
        int play, idx;
        
        public Music(int play, int idx) {
            this.play = play;
            this.idx = idx;
        }
        
        @Override
        public int compareTo(Music m) {
            return Integer.compare(m.play, this.play);
        }
    }
    
    class Genre implements Comparable<Genre> {
        int sum;
        PriorityQueue<Music> pq;
        
        public Genre(int play, int idx) {
            pq = new PriorityQueue();
            add(play, idx);
            
        }
        
        void add(int play, int idx) {
            sum += play;
            pq.offer(new Music(play, idx));
        }
        
        @Override
        public int compareTo(Genre m) {
            return Integer.compare(m.sum, this.sum);
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, Genre> map = new HashMap();
        HashSet<String> set = new HashSet();
        PriorityQueue<Genre> pq = new PriorityQueue();
        
        for(int i = 0; i < genres.length; i++) {
            Genre get = map.get(genres[i]);
            if(get == null) {
                set.add(genres[i]);
                map.put(genres[i], new Genre(plays[i], i));
            } else {
                get.add(plays[i], i);
            }
        }
        
        int arrSize = 0;
        for(String m : set) {
            Genre get = map.get(m);
            arrSize += get.pq.size() >= 2 ? 2 : 1;
            pq.offer(get);
        }
        
        int[] result = new int[arrSize];
        
        int idx = 0;
        while(!pq.isEmpty()) {
            Genre g = pq.poll();
            for(int i = 0; i < 2 && !g.pq.isEmpty(); i++) {
                result[idx++] = g.pq.poll().idx;    
            }
        }
        
        return result;
    }
}







