import java.util.*;
class Solution {
    
    /*
    개인정보 N개
    각 약관마다 개인정보 보관 유효기간이 정해짐 -> 유효기간이 지나면 파기해야 함
    파기해야 할 개인정보의 번호를 오름차순으로 반환해라.
    */
    public int[] solution(String today, String[] terms, String[] privacies)
    {
        // terms: ["A 6", "B 12", "C 3"] <= 20
        // privacies: ["2021.05.02 A"] <= 100
        int[] tArr = new int[3]; 
        int idx = 0;
        for(String t: today.split("\\.")){
            tArr[idx++] = Integer.parseInt(t);
        }
        
        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for(String term: terms){
            String[] temp = term.split(" ");
            map.put(temp[0], Integer.parseInt(temp[1]));
        }
        
        for(int i = 0; i<privacies.length; i++){
            String[] temp = privacies[i].split(" ");
            String[] date = temp[0].split("\\.");
            int year = Integer.parseInt(date[0]);
            int month =  Integer.parseInt(date[1]);
            int day =  Integer.parseInt(date[2]);
            
            int dif = (month + map.get(temp[1])) / 12; // 지금 달에 유효기간 더한 값을 12로 나눔 -> 연도가 바뀌는지 확인
            int mod = map.get(temp[1]) % 12; // 연도 계산에 사용되고 남은 값
            
            if(dif > 0) year += dif; // 연도가 바뀌면 연도 계산
            month = (month + mod)%12; // 월 계산

            // 날짜별 예외 처리
            if(month == 0) { // month를 12로 나누므로 month가 12일때에 대한 예외 처리
                year -= 1;
                month = 12;
            }
            
            if(day - 1 == 0){ // 월 1일일 경우 전 달 28일까지 유효
                month -= 1;
                day = 28;
            }else{
                day -= 1;
            }
            
            // 유효기간 지났는지 확인
            if(tArr[0] > year|| (year == tArr[0] && tArr[1] > month)
               ||(year == tArr[0] && month == tArr[1]) && tArr[2] > day){
                answer.add(i+1);
            }
        }
        
        int[] ans = new int[answer.size()];
        for(int i = 0; i<answer.size(); i++){
            ans[i] = answer.get(i);
        }
        return ans;
    }
}
