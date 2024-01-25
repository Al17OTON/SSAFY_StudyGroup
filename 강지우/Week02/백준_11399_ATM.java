package 강지우.Week02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//5
//3 1 4 3 2
public class 백준_11399_ATM {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] result = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        for(int i = 0; i<n; i++) {
            result[i+1] = arr[i] + result[i];
        }

        int sum = Arrays.stream(result).sum();
        System.out.println(sum);

    }
}

// 스트림 공부용
//    List<Integer> arr = new ArrayList<>();
//    	st = new StringTokenizer(br.readLine());
//                for(int i = 0; i<n; i++) {
//        arr.add(Integer.parseInt(st.nextToken()));
//        }
//
//        Collections.sort(arr);
//
//        List<Integer> lst = IntStream.rangeClosed(0, n)
//        .boxed()
//        .collect(Collectors.toList());
//
//        for (int i = 0; i < n; i++) {
//        lst.set(i + 1, arr.get(i) + lst.get(i));
//        }
//
//        int sum = lst.stream().mapToInt(Integer::intValue).sum();
//        System.out.println(sum);