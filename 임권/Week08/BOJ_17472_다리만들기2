package Week08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_17472_다리만들기2 {
    static class Union {
        int[] uni;

        public Union(int size) {
            uni = new int[size + 1];
            for(int i = 1; i < size + 1; i++) {
                uni[i] = i;
            }
        }

        int find(int a) {
            if(uni[a] == a) return a;
            return uni[a] = find(uni[a]);
        }
        
        boolean setUnion(int a, int b) {
            a = find(a);
            b = find(b);

            if(a == b) return false;

            uni[b] = a;
            return true;
        }
    }

    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
        
    }

    static class Island {
        int r, c, num;

        public Island(int r, int c, int num) {
            this.r = r;
            this.c = c;
            this.num = num;
        }
    }

    static int R, C;
    static int[][] map;

    //섬 검색을 빠르게 하기 위한 위치 리스트
    static ArrayList<Island> island = new ArrayList<>(); 
    //간선리스트
    static PriorityQueue<Edge> edge = new PriorityQueue<>();

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R + 2][C + 2];

        for(int r = 1; r <= R; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 1; c <= C; c++) {
                map[r][c] = Integer.parseInt(st.nextToken()) * -1;  //섬 번호 지정 편의를 위해 -1을 곱해준다.
            }
        }

        int num = 1;
        for(int r = 1; r <= R; r++) {
            for(int c = 1; c <= C; c++) {
                //탐색되지 않은 섬을 찾았다면
                if(map[r][c] == -1) {
                    setIsland(r, c, num);
                    island.add(new Island(r, c, num++));
                }
            }
        }

        //print();

        boolean[][] tmp = new boolean[R + 2][C + 2];
        for(int i = 0; i < island.size(); i++) {
            findShore(island.get(i).r, island.get(i).c, tmp);
        }
        
        //크루스칼로 최소 신장트리 만들기
        //각 섬에 대한 선택 여부
        Union u = new Union(island.size());
        int c = 0, weightSum = 0;
        while(!edge.isEmpty()) {
            Edge e = edge.poll();

            if(u.setUnion(e.from, e.to)) {
                weightSum += e.weight;
                if(++c == island.size() - 1) break;
            }
        }

        if(c != island.size() - 1) weightSum = -1;
        System.out.println(weightSum);
    }

    static void findShore(int r, int c, boolean[][] v) {
        v[r][c] = true;

        for(int i = 0; i < dr.length; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            //해변이 아니라면 
            if(nr > R || nr < 1 || nc > C || nc < 1) continue;
            if(map[nr][nc] != 0) {
                //섬타고 해변찾으러 보내기
                if(!v[nr][nc]) findShore(nr, nc, v);
                continue;
            } 

            //해당 방향으로 다리를 건설해본다.
            setBridge(nr, nc, i, map[r][c], 0);
        }
    }

    static void setBridge(int r, int c, int dir, int num, int len) {
        //basis part : 바다를 건너 육지를 만났다.
        if(map[r][c] != 0) {
            //자신의 섬이거나 길이가 1이면
            if(map[r][c] == num || len == 1) return;

            edge.offer(new Edge(num, map[r][c], len));

            return;
        }

        int nr = r + dr[dir];
        int nc = c + dc[dir];

        if(nr > R || nr < 1 || nc > C || nc < 1) return;

        setBridge(nr, nc, dir, num, len + 1);
    }


    //dfs로 섬의 번호를 지정해준다.
    static void setIsland(int r, int c, int num) {
        map[r][c] = num;
        for(int i = 0; i < dr.length; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if(map[nr][nc] != -1) continue;

            //map[nr][nc] = num;
            setIsland(nr, nc, num);
        }
    }

    static void print() {
        for(int r = 0; r < R + 1; r++) {
            for(int c = 0; c < C + 1; c++) {
                System.out.print(map[r][c] + " ");
            }
            System.out.println();
        }
    }
}
