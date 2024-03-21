import java.util.*;

class Music {
    int cnt;
    int total;
    PriorityQueue<int[]> pq;
    
    Music(int total, int idx, int play) {
        this.cnt = 1;
        this.total = total;
        this.pq = new PriorityQueue<>((o1, o2) -> o1[1] == o2[1] ? o1[0] - o2[0] : o2[1] - o1[1]);
        pq.offer(new int[] {idx, play});
    }
}

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, Music> map = new HashMap<>();
        
        for (int i = 0; i < genres.length; i++) {
            if (map.get(genres[i]) == null) {
                map.put(genres[i], new Music(plays[i], i, plays[i]));
            } else {
                Music music = map.get(genres[i]);
                music.cnt++;
                music.total += plays[i];
                music.pq.offer(new int[] {i, plays[i]});
            }
        }
        
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Music> music = new ArrayList<>(map.values());
        
        music.sort((o1, o2) -> o1.total == o2.total ? o1.cnt - o2.cnt : o2.total - o1.total);

        for (Music m : music) {
            int cnt = 0;
            
            if (m.cnt > 2) {
                cnt = 2;
            } else {
                cnt = m.cnt;
            }
            
            while (cnt-- > 0) {
                list.add(m.pq.poll()[0]);
            }
        }
        
        int[] answer = new int[list.size()];
        
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}
