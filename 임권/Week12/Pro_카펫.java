package Week12;

class Solution {
    public int[] solution(int brown, int yellow) {
        int area = brown + yellow;
        int[] result = new int[2];
        
        for(int r = 3; r < area; r++) {
            if(area % r == 0) {
                int c = area / r;
                int rr = r - 2;
                int cc = c - 2;
                if(rr * cc == yellow) {
                    result[0] = c;
                    result[1] = r;
                    break;
                }
            }
        }
        
        return result;
    }
}

