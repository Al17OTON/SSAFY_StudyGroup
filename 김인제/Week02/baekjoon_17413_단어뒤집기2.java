import java.util.Scanner;
import java.util.Stack;

/*
 * BOJ 17413 단어 뒤집기 2
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<Character> stackC = new Stack<>();

        String str = sc.nextLine();
        boolean open = false;
        String answer = "";

        for (int i = 0; i < str.length(); i++) {
            char nextChar = str.charAt(i);

            if (!open) {
                if (nextChar == ' ') {
                    while (!stackC.empty()) {
                        answer += stackC.pop();
                    }

                    answer += nextChar;
                } else if (nextChar == '<') {
                    while (!stackC.empty()) {
                        answer += stackC.pop();
                    }

                    answer += nextChar;
                    open = true;
                } else {
                    stackC.push(nextChar);
                }
            } else {
                if (nextChar == '>') {
                    answer += nextChar;
                    open = false;
                } else {
                    answer += nextChar;
                }
            }
        }
        
        while (!stackC.empty()) {
            answer += stackC.pop();
        }

        System.out.println(answer.toString());

        sc.close();
    }
}