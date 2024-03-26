package Week10;

import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        HashSet<String> hash = new HashSet();
        
        Arrays.sort(phone_book, (o1, o2)->Integer.compare(o2.length(), o1.length()));
        
        for(String p : phone_book) {
            if(!hash.add(p)) return false;
            
            for(int i = phone_book[phone_book.length - 1].length(); i < p.length(); i++) {
                hash.add(p.substring(0,i));
            }
        }
        return true;
    }
}