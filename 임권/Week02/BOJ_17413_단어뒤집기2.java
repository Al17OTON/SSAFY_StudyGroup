package Week02;

import java.util.Scanner;
import java.util.Stack;

public class BOJ_17413_단어뒤집기2 {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();
        
        boolean flag = false;
        Stack<Character> st = new Stack<>();

        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '<') {
                flag = true;
            } 
            if(flag || s.charAt(i) == ' ') {
                while(!st.empty()) System.out.print(st.pop());
                System.out.print(s.charAt(i));
            } else {
                st.push(s.charAt(i));
            }

            if(s.charAt(i) == '>') {
                flag = false;
            }
        }
        while(!st.empty()) System.out.print(st.pop());
    }
}