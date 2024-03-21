import java.util.*;

class Solution {
    static class Song{
        int num, idx;
        
        Song(int idx, int num){
            this.idx = idx;
            this.num = num;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        Map<String, List<Song>> map = new HashMap<>();
        for(int i = 0; i<genres.length; i++){
            List<Song> temp = map.getOrDefault(genres[i], new ArrayList<>());
            temp.add(new Song(i, plays[i]));
            map.put(genres[i], temp);
        }
        
        Set<String> keySet = map.keySet();
        // 재생 횟수에 따라 우선순위 큐
        PriorityQueue<List<Song>> songs = new PriorityQueue<>((List<Song> l1, List<Song> l2) -> {
            int play1 = 0;
            int play2 = 0;
            for(int i = 0; i<l1.size(); i++){
                play1 += l1.get(i).num;
            }
            
            for(int i = 0; i<l2.size(); i++){
                play2 += l2.get(i).num;
            }
            return play2 - play1;
        });
        
        for(String gen: keySet){
            songs.offer(map.get(gen));
        }
        
        List<Integer> ans = new ArrayList<>();
        while(!songs.isEmpty()){
            List<Song> list = songs.poll();
            Collections.sort(list, (Song s1, Song s2) -> {
                if(s1.num == s2.num) return s1.idx - s2.idx;
                return s2.num - s1.num;
            });
            
            // 장르에 속한 노래가 1개인 경우 + 2개 이상인 경우
            if(list.size() == 1)  ans.add(list.get(0).idx);
            else{
                for(int i = 0; i<2; i++){
                    ans.add(list.get(i).idx);
                }
            }
            
        }

        int[] answer = new int[ans.size()];
        for(int i = 0; i<ans.size(); i++){
            answer[i] = ans.get(i);
        }
        return answer;
    }
}
