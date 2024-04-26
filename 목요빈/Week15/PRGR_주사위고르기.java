import java.util.*;
import java.io.*;

class PRGR_주사위고르기 {
    /*
    n개의 주사위를 나눠가질 때 이길 확률이 가장 높은 조합을 구해라.
    완탐!? -> ㄴㄴ 최악의 경우 10C5 * 6^10임
    
    - 조합으로 nCn/2 경우 구하기
    - A와 B의 모든 경우 구해서 비교해서 승리여부 확인
        1. 순열로 A와 B의 모든 경우 구하기
        2. 반복문 돌며 승패 확인
        
    - 시간을 줄이기 위해 이분탐색 사용 => 주사위의 합을 비교할 때 사용
    */
    
    int N, max, win;
	boolean[] selected;
	boolean[] answer;
	int[][] dices;
	List<Integer> aSum;
    
    public int[] solution(int[][] dice) {
        // dice: 주사위에 쓰인 수의 구성(n) <= 10, dice[N][6]
        dices = dice;
        N = dice.length;
        
		selected = new boolean[N];
		comb(0, 0);
		int idx = 0;
		int[] ans = new int[N/2];
		for (int i = 0; i < N; i++) {
			if(answer[i]) ans[idx++] = i+1;
		}
		
        return ans;
    }
    
     public int binarySearch(int target, List<Integer> list){
        int start = 0;
        int end = list.size()-1;
        while(start <= end){
            int mid = (start + end)/2;
            if(target <= list.get(mid)){ 
                end = mid-1;
            }else{
                start = mid+1;
            }    
        }
        return start;
     }
    
    public void comb(int n, int idx){
        if(n == N/2){
            List<Integer> aCase = new ArrayList<>();
            List<Integer> bCase = new ArrayList<>();
            List<Integer> aSum = new ArrayList<>();
            List<Integer> bSum = new ArrayList<>();
            
            // A: true, B: false
            for (int i = 0; i < N; i++) {
				if(selected[i]) aCase.add(i);
				else bCase.add(i);
			}
            
            win = 0;
            perm(0, 0, aCase, aSum);
            perm(0, 0, bCase, bSum);
            
            Collections.sort(aSum);
            Collections.sort(bSum);
            
            for(int a: aSum){
                win += binarySearch(a, bSum);
            }
            
            if(win > max){
                max = win;
                answer = selected.clone();
            }
            return;
        }
        
        for(int i = idx; i<N; i++){
            if(selected[i]) continue;
            selected[i] = true;
            comb(n+1, i+1);
            selected[i] = false;
        }
    }
    
    public void perm(int n, int sum, List<Integer> list, List<Integer> sumList){
    	if(n == list.size()) {
    		sumList.add(sum);
    		return;
    	}
    	
    	int diceIdx = list.get(n);
    	for (int i = 0; i < 6; i++) {
  			perm(n+1, sum + dices[diceIdx][i], list, sumList);
  		}
    }
}
