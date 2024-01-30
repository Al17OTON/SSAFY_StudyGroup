package 강지우.Week03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 백준_2941_크로아티아_알파벳 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lst = {"c=","c-","dz=","d-","lj","nj","s=","z="};

        String word = br.readLine();

        for (int i = 0; i < lst.length; i++) {
            if(word.contains(lst[i])){
                word = word.replace(lst[i],"!");
            }
        }

        System.out.println(word.length());
    }
}