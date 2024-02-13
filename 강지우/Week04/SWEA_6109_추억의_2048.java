package jiu;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


// 이동할수 있는 방향의 값을 더하면서 진행
// 좌표값 0으로 설정

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
    static int t,n;
    static String sel;
    static int[][] arr;
    
    public static void main(String[] args) throws Exception{
    	System.setIn(new FileInputStream("src/jiu/Solution.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        
        for(int start=1; start<=t; start++) {
        	st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            sel = st.nextToken();
            arr = new int[n][n];
            for(int i=0; i<n; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            if(sel.equals("up")) {
                up();
            }else if(sel.equals("down")) {
                down();
            }else if(sel.equals("left")) {
                left();
            }else if(sel.equals("right")) {
                right();
            }
            
            System.out.printf("#%d\n",start);
            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                	System.out.printf("%d ",arr[i][j]);
                }
                System.out.println();
            }
        }

}
    
    private static void up() {
    	// 해당 라인에 현재 좌표와 같은 값이 있는지 확인 후 현재 좌표 * 2 해줌
    	for(int j=0; j<n; j++) {
    	    for(int i=0; i<n-1; i++) {	// k 를 통해 값을 확인하므로 n-1 
    	        if(arr[i][j] != 0) {
    	            for(int k=i+1; k<n; k++) {		// k -> 이동할수 있는지 확인
    	                if(arr[k][j] != 0) {
    	                    if(arr[i][j] == arr[k][j]) {
    	                        arr[i][j] *= 2;
    	                        arr[k][j] = 0;
    	                    }
    	                    break;
    	                }
    	            }
    	        }
    	    }
    	}

// 배열을 다시 탐색해서 0을 위로 올려줌
//    	현재
//    	[8, 8, 4, 8, 0]
//		[0, 4, 0, 0, 8]
//		[8, 0, 4, 0, 4]
//		[2, 4, 0, 2, 8]
//		[0, 0, 2, 0, 0]
    	

    	
    	int idx = 0;
    	for(int j=0; j<n; j++) {
            for(int i=1; i<n; i++) {	
                if(arr[i][j] != 0) {
                    idx = -1;
                    for(int k=i-1; k>=0; k--) {
                        if(arr[k][j] != 0) {
                            break;
                        }
                        idx = k;
                    }
                    if(idx != -1) {
                        arr[idx][j] = arr[i][j];
                        arr[i][j] = 0;
                    }
                }            
            }
        }
    }

//	이하 동
private static void right() {
	for(int i=0; i<n; i++) {
	    for(int j=n-1; j>0; j--) {
	        if(arr[i][j] != 0) {
	            for(int k=j-1; k>=0; k--) {
	                if(arr[i][k] != 0) {
	                    if(arr[i][j] == arr[i][k]) {
	                        arr[i][j] *= 2;
	                        arr[i][k] = 0;
	                    }
	                    break;
	                }
	            }
	        }
	    }
	}
	
	int idx = 0;
	
	for(int i=0; i<n; i++) {
        for(int j=n-1; j>=0; j--) {
            if(arr[i][j] != 0) {
                idx = -1;
                for(int k=j+1; k<n; k++) {
                    if(arr[i][k] != 0) {
                        break;
                    }
                    idx = k;
                }
                if(idx != -1) {
                    arr[i][idx] = arr[i][j];
                    arr[i][j] = 0;
                }
            }                    
        }
    }
}

private static void left() {
	for(int i=0; i<n; i++) {
	    for(int j=0; j<n-1; j++) {
	        if(arr[i][j] != 0) {
	            for(int k=j+1; k<n; k++) {
	                if(arr[i][k] != 0) {
	                    if(arr[i][j] == arr[i][k]) {
	                        arr[i][j] *= 2;
	                        arr[i][k] = 0;
	                    }
	                    break;
	                }
	            }
	        }
	    }
	}
	int idx = 0;
	
	for(int i=0; i<n; i++) {
        for(int j=1; j<n; j++) {
            if(arr[i][j] != 0) {
                idx = -1;
                for(int k=j-1; k>=0; k--) {
                    if(arr[i][k] != 0) {
                        break;
                    }
                    idx = k;
                }
                if(idx != -1) {
                    arr[i][idx] = arr[i][j];
                    arr[i][j] = 0;
                }
            }                    
        }
    }
}

private static void down() {
	for(int j=0; j<n; j++) {
	    for(int i=n-1; i>0; i--) {
	        if(arr[i][j] != 0) {
	            for(int k=i-1; k>=0; k--) {
	                if(arr[k][j] != 0) {
	                    if(arr[i][j] == arr[k][j]) {
	                        arr[i][j] *= 2;
	                        arr[k][j] = 0;
	                    }
	                    break;
	                }
	            }
	        }
	    }
	}
	
	int idx = 0;
	
	for(int j=0; j<n; j++) {
        for(int i=n-2; i>=0; i--) {
            if(arr[i][j] != 0) {
                idx = -1;
                for(int k=i+1; k<n; k++) {
                    if(arr[k][j] != 0) {
                        break;
                    }
                    idx = k;
                }
                if(idx != -1) {
                    arr[idx][j] = arr[i][j];
                    arr[i][j] = 0;
                }
            }
        }
    }
	}

}

//
//1
//5 up
//4 8 2 4 0
//4 4 2 0 8
//8 0 2 4 4
//2 2 2 2 8
//0 2 2 0 0
//범위설정,,
//리팩토링 매우 필요 -> 설 숙제

