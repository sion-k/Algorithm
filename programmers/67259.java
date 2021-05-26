import java.util.*;

class Solution {
    static int N;
    static int[][] S;
    
    static boolean inRange(int y, int x) { return 0 <= y && y < N && 0 <= x && x < N; }
    
    static final int[] dy = { -1, 1, 0, 0 }; static final int[] dx = { 0, 0, -1, 1 };
    
    // d1, d2방향이 서로 직교하는지 반환
    static boolean orthogonal(int d1, int d2) {
        if (d1 == d2) return false;
        if (d1 > d2) { int temp = d1; d1 = d2; d2 = temp; }
        switch (d1) {
            case 0:case 1: return d2 == 2 || d2 == 3;
            case 2:case 3: return d2 == 0 || d2 == 1;
        }
        return false;
    }
    
    static int dijkstra() {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.offer(new Pair(0, 0, 1, 0)); pq.offer(new Pair(0, 0, 3, 0));
        int[][][] dist = new int[N][N][4];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
        dist[0][0][1] = dist[0][0][3] = 0;
        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            int y = p.y; int x = p.x; int d = p.d; int c = p.cost;
            if (c > dist[y][x][d]) continue;
            for (int dir = 0; dir < 4; dir++) {
                int ny = y + dy[dir]; int nx = x + dx[dir]; int nd = dir;
                if (!inRange(ny, nx) || S[ny][nx] == 1) continue;
                int nextCost = c + 1 + (orthogonal(d, nd) ? 5 : 0);
                if (dist[ny][nx][nd] > nextCost) {
                    dist[ny][nx][nd] = nextCost;
                    pq.offer(new Pair(ny, nx, nd, nextCost));
                }
            }
        }
        return Math.min(dist[N - 1][N - 1][1], dist[N - 1][N - 1][3]);
    }
    
    public int solution(int[][] board) {
        N = board.length; S = board;
        return dijkstra() * 100;
    }

}

class Pair implements Comparable<Pair> {
        int y, x, d; // (y, x)에 위치한 자동차가 바라보고 있는 방향 d : [0, 4) 상하좌우
        int cost;
        
        public Pair (int y, int x, int d, int c) { this.y = y; this.x = x; this.d = d; this.cost = c; }
        
        public int compareTo(Pair o) { return cost - o.cost; }
        
}
