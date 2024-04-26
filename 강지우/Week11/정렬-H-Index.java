import java.util.*;

class Solution {
    public int solution(int[] a) {
        Arrays.sort(a);
        int cnt = a[a.length-1];
        for (int i = cnt; i >= 0; i--) {
            int t = i;
            int idx = (int)Arrays.stream(a).filter(j -> j >= t).count();
            if(idx >= t) {
                return t;
            }
        }
        return 0;
    }
}
