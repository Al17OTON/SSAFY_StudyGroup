package Week02;

import java.util.Scanner;
import java.util.Stack;

/*
 * O(n) ? 이라고 할 수 있나?
 * 스택 자료구조를 사용해서 해결하였습니다.
 */

public class SWEA_1234_비밀번호 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<Character> st = new Stack<>();

        for(int t = 1; t <= 10; t++) {

            int N = sc.nextInt();
            String pw = sc.next();

            for(int i = pw.length() - 1; i >= 0; i--) {     //역순으로 체크, 그렇지 않으면 reverse 연산을 구현해야함.
                char c = pw.charAt(i);
                if(st.empty()) { 
                    st.push(c);
                }
                else {
                    if(st.peek() == c) {     //같은 문자라면 pop
                        st.pop();   
                    }
                    else {
                        st.push(c);
                    }
                }
            }
            System.out.print("#" + t + " ");
            while(!st.empty()) {
                System.out.print(st.pop());         //스택이 빌때까지 출력
            }
            System.out.println();
        }

    }
}