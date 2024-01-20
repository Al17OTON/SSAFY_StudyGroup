package swea.d3_7272;

import java.io.*;
import java.util.*;

// swea d3 7272 : 안경이 없어!
public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
         
        for(int test_case = 1; test_case <= T; test_case++)
        {
            String a = sc.next();
            String b = sc.next();
            boolean flag = true;
             
            if(a.length() != b.length()) flag = false;
            else {
                for(int i = 0; i<a.length(); i++) {
                    if(count(a.substring(i, i+1)) != count(b.substring(i, i+1))) {
                        flag = false;
                        break;
                    }
                }
            }

            System.out.println("#" + test_case + (flag ? " SAME" : " DIFF"));
        }
        sc.close();
	}
	
	static int count(String s) {
        int result = 0;
        String[] one = {"A", "D", "O", "P", "Q", "R"};
         
        for(String str: one) {
            if (s.equals(str)) result = 1;
            else if(s.equals("B")) result = 2;
        }
        return result;
    }
}
