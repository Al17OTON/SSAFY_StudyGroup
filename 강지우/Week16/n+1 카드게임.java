import java.util.*;
class Solution {
    public int solution(int coin, int[] cards) {
        int answer = 0;
        Set<Integer> org = new HashSet<>();
        Set<Integer> orgAdd = new HashSet<>();
        int size = cards.length;
        int target = size + 1;
        int idx = size/3;
        
        for(int i = 0; i<idx; i++){
            org.add(cards[i]);
        }
        
        
        System.out.println(org);
        
        while(true){
            answer++;
            if(idx >= size){
                break;
            }
            
            boolean flag = false;
            orgAdd.add(cards[idx]);
            orgAdd.add(cards[idx+1]);
            
            idx += 2;
            
            for(int i : org){
                if(org.contains(target - i)){
                    org.remove(target - i);
                    org.remove(i);
                    flag = true;
                    break;
                }
            }
            
            if(!flag){
                if(coin > 0){
                    for(int i : org){
                        if(orgAdd.contains(target - i)){        
                            orgAdd.remove(target - i);
                            org.remove(i);
                            coin--;
                            flag = true;
                            break;
                        }
                    }
                }
            }
            
            if(!flag){
               if(coin > 1){
                    for(int i : orgAdd){
                        if(orgAdd.contains(target - i)){        
                            orgAdd.remove(target - i);
                            orgAdd.remove(i);
                            coin -= 2;
                            flag = true;
                            break;
                        }
                    }
                }
            }
            
            if(!flag){
                break;
            }
        }
        return answer;
    }
}