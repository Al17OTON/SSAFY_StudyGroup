import java.util.*;

class SWEA_1234_비밀번호
{
    static char numArr[];
    static int rightIdx;
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
         
        for(int test_case = 1; test_case <= 10; test_case++)
        {
            int n=sc.nextInt();
            String nums=sc.nextLine();
             
            System.out.println("#"+test_case+" "+getPassword(n,nums));
        }
    }
    public static String getPassword(int n, String nums) {
        numArr=nums.toCharArray();
        ArrayDeque<Character> stack=new ArrayDeque<>();
         
        rightIdx=1;
        stack.push(numArr[0]);
        while(rightIdx<=n) {
            if(stack.peek()==numArr[rightIdx]) {
                rightIdx++;
                stack.pop();
            }else {
                stack.push(numArr[rightIdx]);
                rightIdx++;
            }
        }
        int pwLen=stack.size();
        String ans="";
        List<Character>list=new ArrayList<>();
        for (int i = 0; i < pwLen; i++) {
            ans+=stack.pollLast();
        }
        //Collections.reverse(list);
        return ans;
    }
}
/*
n<=100
완탐: for 첨부터 끝까지 돌면서 연속된 숫자 있으면 소거. => O(n*n/2)
-윈도우 슬라이싱? right와 left의 인덱스 변화?

- list를 써서 특정 위치 삭제? n값이 작아서 가능은 함

- 특정 위치에서 넣어다 뺏다 값 비교=>스택을 통해

stack은 사용을 지양함. 마지막에 reverse 안해도 되는 deque를 사용-> 양쪽으로 삽입 삭제 가능
*/