// swea 7272 Java

import java.util.Scanner;

class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            String firstInput = sc.next();
            String secondInput = sc.next();
            String first = check(firstInput);
            String second = check(secondInput);

            if(first.equals(second))
                System.out.printf("#%d %s\n", test_case, "SAME");
            else
                System.out.printf("#%d %s\n", test_case, "DIFF");
        }
    }

    public static String check (String word) {
        char[] zero = {'C','E','F','G','H','I','J','K','L','M','N','S','T','U','V','W','X','Y','Z'};
        char[] one = {'A','D','O','P','Q','R'};
        String result = "";

        for(int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            boolean getTarget = false;

            if(getTarget == false) {
                for(char target: zero) {
                    if(letter == target) {
                        result += "Z";
                        getTarget = true;
                    }
                }
            }

            if(getTarget == false) {
                for(char target: one) {
                    if(letter == target) {
                        result += "O";
                        getTarget = true;
                    }
                }
            }

            if(letter == 'B')
                result += "B";
        }

        return result;
    }
}