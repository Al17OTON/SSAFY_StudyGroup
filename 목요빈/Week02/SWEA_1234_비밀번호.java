package 목요빈.Week02;

import java.io.*;
import java.util.*;

// SWEA D3 1234 : 비밀번호
public class SWEA_1234_비밀번호 {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int T = 10;
 
        for(int test_case = 1; test_case <= T; test_case++)
        {
             
            int N = sc.nextInt();
            String str = sc.next();
            Stack<String> stk = new Stack<>();
             
            for(int i = 0; i<N; i++) {
                if(stk.empty())
                    stk.push(str.substring(i, i+1));
                else if(stk.peek().equals(str.substring(i, i+1)))
                    stk.pop();
                else
                    stk.push(str.substring(i, i+1));
            }
             
            int size = stk.size();
            String[] pwd = new String[stk.size()];
            for(int i = stk.size()-1; i>=0; i--) {
                pwd[i] = stk.pop();
            }
             
            StringBuilder sb = new StringBuilder();
            sb.append("#" + test_case + " ");
            for(int i = 0; i<size; i++)
                sb.append(pwd[i]);
            System.out.println(sb);
        }
        sc.close();
	}
}
