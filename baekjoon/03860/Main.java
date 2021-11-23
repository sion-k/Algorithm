import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    // 묘비가 있는지 여부
    static boolean[][] S;
    // H[i][j] != null이라면 귀신 구멍이 존재하는 위치
    static Pair[][] H;

    static final int[] dy = { -1, 1, 0, 0 };
    static final int[] dx = { 0, 0, -1, 1 };
    static final long INF = Long.MAX_VALUE;

    static boolean inRange(int y, int x) { return 0 <= y && y < N && 0 <= x && x < M; }

    static long[][] bellmanFord() {
        long[][] dist = new long[N][M];
        for (int i = 0; i < N; i++) Arrays.fill(dist[i], INF);
        dist[0][0] = 0;
        for (int k = 0; k < N * M; k++) {
            boolean updated = false;
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < M; x++) {
                    if (dist[y][x] == INF) continue;
                    if (y == N - 1 && x == M - 1) continue;
                    if (H[y][x] != null) {
                        int ny = H[y][x].y; int nx = H[y][x].x; int nk = H[y][x].k;
                        if (dist[ny][nx] > dist[y][x] + nk) {
                            dist[ny][nx] = dist[y][x] + nk;
                            updated = true;
                            
                        }
                    } else {
                        for (int d = 0; d < 4; d++) {
                            int ny = y + dy[d]; int nx = x + dx[d];
                            if (!inRange(ny, nx) || S[ny][nx]) continue;
                            if (dist[ny][nx] > dist[y][x] + 1) {
                                dist[ny][nx] = dist[y][x] + 1;
                                updated = true;
                            }
                        }
                    }
                }
            }
            if (updated && k == N * M - 1) return null;
        }
        return dist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        String line = "";
        while (!(line = br.readLine()).equals("0 0")) {
            StringTokenizer st = new StringTokenizer(line, " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            S = new boolean[N][M];
            int G = Integer.parseInt(br.readLine());
            for (int i = 0; i < G; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                S[y][x] = true;
            }
            H = new Pair[N][M];
            int E = Integer.parseInt(br.readLine());
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int y1 = Integer.parseInt(st.nextToken());
                int x1 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());
                int x2 = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());
                H[y1][x1] = new Pair(y2, x2, k);
            }
            long[][] ret = bellmanFord();
            if (ret == null) bw.append("Never");
            else if (ret[N - 1][M - 1] == INF) bw.append("Impossible");
            else bw.append(ret[N - 1][M - 1]);
            bw.append("\n");
        }
        System.out.print(bw);
    }

}
class Pair {
    int y, x, k;

    Pair(int y, int x, int k) { this.y = y; this.x = x; this.k = k; }
}