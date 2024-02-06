import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/* BOJ 16234. 인구 이동 */
public class Problem16234 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input/boj/Input16234.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int[][] countries = new int[N][N];

        for (int i = 0; i < countries.length; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < countries[i].length; j++) {
                countries[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;

        while (true) {
            boolean[][] visited = new boolean[N][N];
            Queue<int[]> queue = new LinkedList<>();
            List<List<int[]>> unions = new ArrayList<>();
            List<Integer> avgs = new ArrayList<>();
            boolean flag = false;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        List<int[]> union = new ArrayList<>();
                        int sum = countries[i][j];
                        queue.add(new int[] {i, j});
                        union.add(new int[] {i, j});
                        visited[i][j] = true;

                        while (!queue.isEmpty()) {
                            int[] coord = queue.remove();

                            for (int k = 0; k < dir.length; k++) {
                                int[] nextCoord = {coord[0] + dir[k][0], coord[1] + dir[k][1]};

                                if (nextCoord[0] >= 0 && nextCoord[0] < N && nextCoord[1] >= 0 && nextCoord[1] < N
                                        && !visited[nextCoord[0]][nextCoord[1]]
                                        && Math.abs(countries[coord[0]][coord[1]] - countries[nextCoord[0]][nextCoord[1]]) >= L
                                        && Math.abs(countries[coord[0]][coord[1]] - countries[nextCoord[0]][nextCoord[1]]) <= R) {
                                    sum += countries[nextCoord[0]][nextCoord[1]];
                                    queue.add(nextCoord);
                                    union.add(nextCoord);
                                    flag = true;
                                    visited[nextCoord[0]][nextCoord[1]] = true;
                                }
                            }
                        }

                        unions.add(union);
                        avgs.add(sum / union.size());
                    }
                }
            }

            for (int i = 0; i < unions.size(); i++) {
                for (int j = 0; j < unions.get(i).size(); j++) {
                    int[] target = unions.get(i).get(j);
                    countries[target[0]][target[1]] = avgs.get(i);
                }
            }

            if (flag) {
                answer++;
            } else {
                break;
            }
        }

        System.out.println(answer);

        br.close();
    }
}
