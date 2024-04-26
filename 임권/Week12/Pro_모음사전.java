package Week12;

class Solution {
    
    char[] character = {'A', 'E', 'I', 'O', 'U'};
    String word;
    int count = 0;
    
    public int solution(String word) {
        this.word = word;
        dfs(0, new char[5]);
        return count - 1;
    }
    
    boolean dfs(int idx, char[] sel) {
        count++;
        if(idx == word.length()) {
            boolean result = true;
            for(int i = 0; i < idx; i++) {
                if(sel[i] != word.charAt(i)) {
                    result = false;
                    break;
                }
            }
            if(result) return true;
        }
        if(idx == sel.length) return false;
        
        for(int i = 0; i < character.length; i++) {
            sel[idx] = character[i];
            if(dfs(idx + 1, sel)) return true;
        }
        
        return false;
    }
}
