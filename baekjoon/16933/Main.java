import java.util.*;
import java.io.*;

public class Main {
    static int N, M, K;
    static boolean[][] S;

    static final int[] dy = { -1, 1, 0, 0 };
    static final int[] dx = { 0, 0, -1, 1 };

    static boolean inRange(int y, int x) { return 0 <= y && y < N && 0 <= x && x < M; }

    static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { 0, K, 0, 0 });
        int[][][][] dist = new int[2][K + 1][N][M];
        for (int k = 0; k < K + 1; k++) {
            for (int i = 0; i < N; i++) {
                Arrays.fill(dist[0][k][i], -1);
                Arrays.fill(dist[1][k][i], -1);
            }
        }
        dist[0][K][0][0] = 0;
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int y = p[2]; int x = p[3]; int k = p[1]; int t = p[0];
            // 이동하는 경우
            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d]; int nx = x + dx[d];
                if (inRange(ny, nx)) {
                    // 이동할 수 있는 경우
                    if (S[ny][nx]) {
                        if (dist[t ^ 1][k][ny][nx] == -1) {
                            q.offer(new int[] { t ^ 1, k, ny, nx });
                            dist[t ^ 1][k][ny][nx] = dist[t][k][y][x] + 1;
                        }
                    // 이동할 수 없지만 부술 수 있는 경우
                    } else if (k > 0 && t == 0) {
                        if (dist[t ^ 1][k - 1][ny][nx] == -1) {
                            q.offer(new int[] { t ^ 1, k - 1, ny, nx });
                            dist[t ^ 1][k - 1][ny][nx] = dist[t][k][y][x] + 1;
                        }
                    }
                }
            }
            // 이동하지 않는 경우
            if (dist[t ^ 1][k][y][x] == -1) {
                q.offer(new int[] { t ^ 1, k, y, x });
                dist[t ^ 1][k][y][x] = dist[t][k][y][x] + 1;
            }
        }
        int min = Integer.MAX_VALUE;
        for (int k = 0; k <= K; k++) {
            if (dist[0][k][N - 1][M - 1] != -1)
                min = Math.min(min, dist[0][k][N - 1][M - 1]);
            if (dist[1][k][N - 1][M - 1] != -1)
                min = Math.min(min, dist[1][k][N - 1][M - 1]);
        }
        return min;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        S = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) S[i][j] = line.charAt(j) == '0';
        }
        int ret = bfs();
        if (ret == Integer.MAX_VALUE) ret = -2;
        System.out.println(ret + 1);
    }

}
