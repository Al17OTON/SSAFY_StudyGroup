package jiu;

import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int from, to, weight;

        public Node(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static class Corner {
        int x, y, num;

        public Corner(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }

    static BufferedReader br;
    static StringTokenizer st;
    static int n, m, V, E, ans, nodeCnt;
    static int[][] arr, dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int cnt = 1;
    static int[] set;
    static List<Corner> corner_lst;
    static List<Node> edgeList;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        corner_lst = new ArrayList<>();
        edgeList = new ArrayList<>();

        // 섬을 -1 로 표기 숫자셀때 편함
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int num = Integer.parseInt(st.nextToken());
                arr[i][j] = num == 1 ? -1 : num;
            }
        }

        // 섬개수 표시 
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == -1) {
                    dfs(i, j);
                    cnt++;
                }
            }
        }

        cornerCheck();		// 외곽 판단 후 저장
        makeEdgeList();		// 결과값 간선리스트로 변경
        ans = kruskal();
        System.out.println(nodeCnt != V - 1 ? -1 : (ans == 0 ? -1 : ans));
    }

    private static void makeEdgeList() {
        for (int i = 0; i < corner_lst.size(); i++) {
            for (int j = i + 1; j < corner_lst.size(); j++) {
                Corner island1 = corner_lst.get(i);
                Corner island2 = corner_lst.get(j);
                getDistance(island1.x, island2.x, Math.min(island1.y, island2.y), Math.max(island1.y, island2.y),
                        island1.num, island2.num, true);
                getDistance(island1.y, island2.y, Math.min(island1.x, island2.x), Math.max(island1.x, island2.x),
                        island1.num, island2.num, false);
            }
        }
    }

//  x,y,nx,ny 좌표 별 비교를 통해 이동할수 있는 거리값 -> 간선리스트로 변경
    private static void getDistance(int x, int nx, int y, int ny, int num1, int num2, boolean ck) {
        if (x == nx && num1 != num2) {
            if (bridgeCheck(x, y, ny, ck)) {
                int dis = Math.abs(y - ny) - 1;
                if (dis >= 2) {
                    edgeList.add(new Node(num1, num2, dis));
                }
            }
        }
    }

//  가로와 세로로 이동할때 이동하는 길이 0인지 확인해야함
    private static boolean bridgeCheck(int x, int dy, int ny, boolean direction) {
        for (int i = dy + 1; i < ny; i++) {
            if (direction) {
                if (arr[x][i] != 0) {
                    return false;
                }
            } else {
                if (arr[i][x] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

//  외각 체크
    private static void cornerCheck() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] != 0) {
                    for (int[] d : dir) {
                        int nx = i + d[0];
                        int ny = j + d[1];

                        if (!check(nx, ny)) {
                            continue;
                        }

                        if (arr[nx][ny] == 0) {
                            corner_lst.add(new Corner(i, j, arr[i][j]));
                        }
                    }
                }
            }
        }
    }

    private static void dfs(int i, int j) {
        if (check(i, j)) {
            if (arr[i][j] == -1) {
                arr[i][j] = cnt;

                dfs(i, j + 1);
                dfs(i + 1, j);
                dfs(i, j - 1);
                dfs(i - 1, j);
            }
        }
    }

    private static boolean check(int i, int j) {
        if (i < 0 || i >= n || j < 0 || j >= m) {
            return false;
        }
        return true;
    }

    private static int kruskal() {
        Collections.sort(edgeList);
        makeSet();
        nodeCnt = 0;
        int ans = 0;
        for (Node node : edgeList) {
            if(!union(node.from, node.to)){
                continue;
            }
            ans += node.weight;
            if(++nodeCnt == V-1){
                break;
            }
        }
        return ans;
    }

    private static boolean union(int from, int to) {
        int a = find(from);
        int b = find(to);
        if(a == b){
            return false;
        }
        set[a] = b;
        return true;
    }

    private static void makeSet() {
        V = cnt-1;
        E = edgeList.size();
        set = new int[V+1];
        for (int i = 0; i < V+1; i++) {
            set[i] = i;
        }
    }

    private static int find(int from) {
        if(set[from] == from){
            return set[from];
        }
        return set[from] = find(set[from]);
    }
}