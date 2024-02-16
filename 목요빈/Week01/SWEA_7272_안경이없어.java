package 목요빈.Week01;

import java.io.*;
import java.util.*;

// SWEA d3 7272 : 안경이 없어!
public class SWEA_7272_안경이없어 {

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
        String[] one = {"A", "D", "O", "P", "Q", "R"};

        if(s.equals("B")) 
        	return 2;
        for(String str: one) {
            if (s.equals(str)) 
            	return 1;
        }
        return 0;
    }
}
