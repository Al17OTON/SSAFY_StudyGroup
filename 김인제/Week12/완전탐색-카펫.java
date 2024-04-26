class Solution {
    public int[] solution(int brown, int yellow) {
        int height = 1;
        int[] answer = new int[2];
        
        for (int i = 8; i < brown; i += 2) {
            height++;
        }
        
        for (int i = 1; i <= height; i++) {
            if (yellow % i == 0 && (brown / 2) - i - 2 == yellow / i) {
                answer[0] = yellow / i + 2;
                answer[1] = i + 2;
                break;
            }
        }

        return answer;
    }
}
