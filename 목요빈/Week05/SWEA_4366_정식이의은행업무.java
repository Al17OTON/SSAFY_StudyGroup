package 목요빈.Week05;

import java.io.*;
import java.util.*;
 
public class SWEA_4366_정식이의은행업무 {
 
    static String binary, trinary;
    static StringBuilder sb;
    static List<Integer> list;
    static int ans;
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
 
        for (int test_case = 1; test_case <= T; test_case++) {
            sb = new StringBuilder();
            binary = br.readLine(); // <= 40
            trinary = br.readLine();
 
            int b = Integer.parseInt(binary, 2);
            int t = Integer.parseInt(trinary, 3);
            list = new ArrayList<>();
            ans = 0;
 
            for (int i = 0; i < binary.length(); i++) {
                if (binary.charAt(i) == '0') { // 0 -> 1;
                    list.add(b + (int) Math.pow(2, binary.length() - i - 1));
                } else { // '1' : 1 -> 0
                    list.add(b - (int) Math.pow(2, binary.length() - i - 1));
                }
            }
 
            sb.append("#" + test_case + " ");
            for (int i = 0; i < trinary.length(); i++) {
                char cur = trinary.charAt(i);
                int base = (int) Math.pow(3, trinary.length() - i - 1);
                if (cur == '0') { // 0 -> 1/2;
                    if (isContained(t + base)) break;
                    if (isContained(t + base*2)) break;
                } else if(cur == '1'){ // 1 -> 0/2
                    if (isContained(t - base)) break;
                    if(isContained(t + base)) break;
                } else { // 2 -> 0/1
                    if (isContained(t - base)) break;
                    if(isContained(t - base*2)) break;
                }
            }
            System.out.println(sb);
        }
    }
 
    static boolean isContained(int num) {
        if (list.contains(num)) {
            sb.append(num);
            return true;
        }
        return false;
    }
}