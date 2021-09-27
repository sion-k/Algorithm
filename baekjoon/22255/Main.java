import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[][] S;

    static int[][] dy = {
        { -1, 1, 0, 0 },
        { -1, 1 },
        { 0, 0 }
    };

    static int[][] dx = {
        { 0, 0, -1, 1 },
        { 0, 0 },
        { -1, 1 }
    };

    static boolean inRange(int y, int x) { return 0 <= y && y < N && 0 <= x && x < M; }

    static final int INF = 987654321;

    static int dijkstra(int y1, int x1, int y2, int x2) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.offer(new Pair(y1, x1, 1, 0));
        int[][][] dist = new int[N][M][3];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                Arrays.fill(dist[i][j], INF);
        dist[y1][x1][1] = 0;
        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            int y = p.y; int x = p.x; int t = p.t; int c = p.cost;
            if (dist[y][x][t] < c) continue;
            for (int d = 0; d < dy[t].length; d++) {
                int ny = y + dy[t][d]; int nx = x + dx[t][d];
                if (!inRange(ny, nx) || S[ny][nx] == -1) continue;
                int nt = (t + 1) % 3; int nc = c + S[ny][nx];
                if (dist[ny][nx][nt] > nc) {
                    dist[ny][nx][nt] = nc;
                    pq.offer(new Pair(ny, nx, nt, nc));
                }
            }
        }
        int min = INF;
        for (int t = 0; t < 3; t++) min = Math.min(min, dist[y2][x2][t]);
        return min == INF ? -1 : min;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        S = new int[N][M];
        st = new StringTokenizer(br.readLine(), " ");
        int y1 = Integer.parseInt(st.nextToken()) - 1;
        int x1 = Integer.parseInt(st.nextToken()) - 1;
        int y2 = Integer.parseInt(st.nextToken()) - 1;
        int x2 = Integer.parseInt(st.nextToken()) - 1;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) S[i][j] = Integer.parseInt(st.nextToken());
        }
        System.out.println(dijkstra(y1, x1, y2, x2));
    }

}
class Pair implements Comparable<Pair> {
    int y, x, t, cost;

    Pair (int y, int x, int t, int cost) { 
        this.y = y; this.x = x; this.t = t; this.cost = cost;
    }

    @Override
    public int compareTo(Pair o) { return cost - o.cost; }
}
