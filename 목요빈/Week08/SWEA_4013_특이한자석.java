package 목요빈.Week08;

import java.io.*;
import java.util.*;

public class SWEA_4013_특이한자석 {
	
	static int K;
	static int[][] magnet;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			K = Integer.parseInt(br.readLine()); // 자석 회전 횟수
			magnet = new int[4][8];
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					magnet[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
            for(int i = 0; i<K; i++){
            	st = new StringTokenizer(br.readLine());
                int num = Integer.parseInt(st.nextToken())-1; // 회전시킬 자석 번호
                int dir = Integer.parseInt(st.nextToken()); // 회전시킬 방향(1: 시계 방향, -1: 반시계 방향)
            
                // 도는지 안도는지부터 체크 후 일괄 회전
                int[] rotate = new int[4];
                rotate[num] = dir;
                
                int left = num-1;
                while(left >= 0) { // 왼쪽 회전
                	if(magnet[left][2] != magnet[left+1][6]) {
                		rotate[left] = rotate[left+1] * (-1);
                	}
                	left -= 1;
                }
                
                int right = num+1;
                while(right < 4) { // 오른쪽 회전
                	if(magnet[right][6] != magnet[right-1][2]) {
                		rotate[right] = rotate[right-1] * (-1);
                	}
                	right += 1;
                }
                
                rotate(rotate);
            }
            
//            for (int[] m : magnet) {
//            	System.out.println(Arrays.toString(m));
//			}
            int score = 0;
            for (int i = 0; i < 4; i++) {
				if(magnet[i][0] == 1) score += Math.pow(2, i);
			}
            System.out.printf("#%d %d\n", t, score);
		}
	}
    
    static void rotate(int[] rotate){
    	for (int i = 0; i < 4; i++) {
			// i번째 자석을 rotate[i] 방향으로 회전
    		int dir = rotate[i];
    		int[] temp = new int[8];
    		if(dir == 0) continue;
    		else if(dir == 1) { // 시계방향
    			for (int j = 0; j < 8; j++) {
    				temp[j] = magnet[i][(j-1+8)%8];
				}
    		}else { // 반시계 방향
    			for (int j = 0; j < 8; j++) {
    				temp[j] = magnet[i][(j+1)%8];
				}
    		}
    		magnet[i] = temp.clone();
		}
    }
}
