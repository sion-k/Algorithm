import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[][] S;

    static final int[] dy = { -1, 1, 0, 0 };
    static final int[] dx = { 0, 0, -1, 1 };

    static boolean inRange(int y, int x) { return 0 <= y && y < N && 0 <= x && x < M; }
    
    static final int INF = 987654321;

    static int dijkstra() {
        PriorityQueue<Tuple> pq = new PriorityQueue<>();
        if (S[0][0] == -1) return -1;
        pq.offer(new Tuple(0, 0, S[0][0]));
        int[][] dist = new int[N][M];
        for (int i = 0; i < N; i++) Arrays.fill(dist[i], INF);
        dist[0][0] = S[0][0];
        while (!pq.isEmpty()) {
            Tuple t = pq.poll();
            int y = t.y; int x = t.x; int c = t.cost;
            if (dist[y][x] < c) continue;
            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d]; int nx = x + dx[d];
                if (inRange(ny, nx) && S[ny][nx] != -1) {
                    int nc = c + S[ny][nx];
                    if (dist[ny][nx] > nc) {
                        dist[ny][nx] = nc;
                        pq.offer(new Tuple(ny, nx, nc));
                    }
                }
            }
        }
        return dist[N - 1][M - 1] == INF ? -1 : dist[N - 1][M - 1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        S = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) S[i][j] = Integer.parseInt(st.nextToken());
        }
        System.out.println(dijkstra());
    }

}
class Tuple implements Comparable<Tuple> {
    int y, x, cost;

    Tuple(int y, int x, int c) {
        this.y = y; this.x = x;
        cost = c;
    }

    @Override
    public int compareTo(Tuple t) { return cost - t.cost; }
}
