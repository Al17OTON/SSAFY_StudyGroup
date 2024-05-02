import java.util.*;

class Solution {
    
    public int solution(int coin, int[] cards) {
        // coin <= n <= 1000, n은 6의 배수
        Set<Integer> card = new HashSet<>();
        Set<Integer> tempCard = new HashSet<>();
        
        int N = cards.length;
        for(int i = 0; i<N/3; i++){
            card.add(cards[i]);
        }
        
        int round = 0;
        int myCoin = coin;
        int idx = N/3;
        L: while(idx < N){
            // 뽑은 카드 2개 넣어주기
            tempCard.add(cards[idx++]);
            tempCard.add(cards[idx++]);
            
            // 가진 카드 중에서 2개 고르기
            for(int c: card){
                if(card.contains(N+1-c)){
                    card.remove(c);
                    card.remove(N+1-c);
                    round += 1;
                    continue L;
                }
            }
            
            if(myCoin > 0){
                // 가진 카드에서 1개, 안뽑은 카드에서 1개 고르기
                for(int c: card){
                    if(tempCard.contains(N+1-c)){
                        card.remove(c);
                        tempCard.remove(N+1-c);
                        myCoin -= 1;
                        round += 1;
                        continue L;
                    }
                }
                
                // 안뽑은 카드에서 2개 고르기
                if(myCoin > 1){
                    for(int c: tempCard){
                        if(tempCard.contains(N+1-c)){
                            tempCard.remove(c);
                            tempCard.remove(N+1-c);
                            myCoin -= 2;
                            round += 1;
                            continue L;
                        }
                    }
                }
            }
            break;
        }
        
        return round + 1;
    } 
}

/*
1~n 카드와 동전을 이용해 게임하려고 함
카드 뽑는 순서 정해져있음
게임 방식
- 카드 뭉치에서 카드 n/3 뽑기
- 라운드 시작
    1. 카드뭉치에서 2장 뽑음 -> 카드 뭉치에 남은 카드가 없으면 게임 종료
    2. 뽑은 카드는 동전을 소모해 가지거나, 버릴 수 있음
  => 카드의 적힌 수의 합이 n+1이 되도록 카드 두 장을 내고 다음 라운드
  두 장을 낼 수 없다면 게임 종료
  
  Q. 게임에서 도달 가능한 최대 라운드 수? 
  
  완탐?
  
  * 동전을 최소로 사용해야 라운드를 오래 갈 수 있음
  * x와 짝이 되는 카드는 무조건 1개 -> 나중에 더 나은 경우가 있지 않을까 하는 경우 없음!!!
  * 카드 뽑는 순서가 있는데 어떻게 나중에 쓰지?라고 생각했는데 일단 뽑았다 치는거임
  
*/
