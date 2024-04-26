import java.util.*;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        int i;
        for(i = n - 1; i >= 0; i--) {
            if(deliveries[i] != 0 || pickups[i] != 0) break;
        }
        n = i;
        deliveries = Arrays.copyOfRange(deliveries, 0, i + 1);
        pickups = Arrays.copyOfRange(pickups, 0, i + 1);
        
        long result = 0;
        int del = n, pic = n;
        
        while(del >= 0 || pic >= 0) {
            int delCnt = 0, picCnt = 0, delTmp = del, picTmp = pic;
            
            while(delTmp >= 0) {
                int sub = cap - delCnt;
                if(sub >= deliveries[delTmp]) {
                    delCnt += deliveries[delTmp--];
                } else {
                    deliveries[delTmp] -= sub;
                    break;
                }
            }
            
            while(picTmp >= 0) {
                int sub = cap - picCnt;
                if(sub >= pickups[picTmp]) {
                    picCnt += pickups[picTmp--];
                } else {
                    pickups[picTmp] -= sub;
                    break;
                }
            }
            
            //System.out.println((Math.max(del, pic) + 1));
            result += (Math.max(del, pic) + 1) * 2;
            
            del = delTmp;
            pic = picTmp;
        }
         
        return result;
    }
}