class Solution {
    static int idx = 0;
    static int answer;
    static String word;
    static char[] words = {'A', 'E', 'I', 'O', 'U'};
    
    public int solution(String word) {
        this.word = word;

        combi(0, "");
        
        return answer;
    }
    
    static void combi(int depth, String str) {
        if (answer != 0 || depth == 6) {
            return;
        }

        if (str.equals(word)) {
            answer = idx;

            return;
        }
        
        idx++;

        for (int i = 0; i < 5; i++) {
            combi(depth + 1, str + words[i]);
        }
    }
}
