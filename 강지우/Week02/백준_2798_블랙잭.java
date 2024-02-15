package 강지우.Week02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//5 21
//5 6 7 8 9
public class 백준_2798_블랙잭 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int ans = 0;

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i = 0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i<n; i++) {
            int num = 0;
            for(int j = i+1; j<n; j++) {
                for(int k = j+1; k<n; k++) {
                    num = arr[i]+arr[j]+arr[k];
                    if(num<=m) {
                        ans = Math.max(ans, num);
                    }
                }
            }
        }
        System.out.println(ans);


    }
}

// 재귀로 구현한 조합을 이용한 풀이
//    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    StringTokenizer st = new StringTokenizer(br.readLine());
//    int n = Integer.parseInt(st.nextToken());
//    int m = Integer.parseInt(st.nextToken());
//        st = new StringTokenizer(br.readLine());
//                int[] arr = new int[n];
//                for(int i = 0; i<n; i++) {
//        arr[i] = Integer.parseInt(st.nextToken());
//        }
//
//        List<List<Integer>> comb = comb(arr, 3);
//        List<Integer> ansLst = new ArrayList<>();
//
//        for (List<Integer> c : comb) {
//        int sum = c.stream().mapToInt(Integer::intValue).sum();
//        ansLst.add(sum);
//        }
//        Collections.sort(ansLst, Collections.reverseOrder());
//
//        for (Integer ans : ansLst) {
//        if(ans<=m) {
//        System.out.println(ans);
//        break;
//        }
//        }
//        }
//
//private static List<List<Integer>> comb(int[] arr, int r) {
//        List<List<Integer>> result = new ArrayList<>();
//        combMaker(arr, 0, new ArrayList<>(), r, result);
//        return result;
//        }
//
//private static void combMaker(int[] arr, int start, List<Integer> cnt, int r, List<List<Integer>> result) {
//        if (r == 0) {
//        result.add(new ArrayList<>(cnt));
//        return;
//        }
//
//        for (int i = start; i < arr.length; i++) {
//        cnt.add(arr[i]);
//        combMaker(arr, i + 1, cnt, r - 1, result);
//        cnt.remove(cnt.size() - 1);
//        }