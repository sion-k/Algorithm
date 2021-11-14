import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static char[][] S;

    static final int[] dy = { -1, 1, 0, 0 };
    static final int[] dx = { 0, 0, -1, 1 };

    static boolean inRange(int y, int x) { return 0 <= y && y < N && 0 <= x && x < M; }

    static final int INF = 987654321;

    static int[][][] bfs(int[] u, int[] v) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(u);
        // (y, x, 상하좌우) (4인 경우 초기값)
        int[][][] dist = new int[N][M][5];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(dist[i][j], -1);
            }
        }
        dist[u[0]][u[1]][u[2]] = 0;
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int y = p[0]; int x = p[1]; int d = p[2];
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i]; int nx = x + dx[i]; int nd = i;
                if (!inRange(ny, nx) || S[ny][nx] == '#' || nd == d || dist[ny][nx][nd] != -1) continue;
                q.offer(new int[] { ny, nx, nd });
                dist[ny][nx][nd] = dist[y][x][d] + 1;
            }
        }
        return dist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        S = new char[N][M];
        int[] start = new int[2];
        int[] c1 = null;
        int[] c2 = null;
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                S[i][j] = line.charAt(j);
                if (S[i][j] == 'S') start = new int[] { i, j, 4 };
                if (S[i][j] == 'C') {
                    if (c1 == null) {
                        c1 = new int[] { i, j };
                    } else {
                        c2 = new int[] { i, j };
                    }
                }
            }
        }
        int ret = Integer.MAX_VALUE;
        // S-C1-C2
        int[][][] dist1 = bfs(start, c1);
        for (int i = 0; i < 4; i++) {
            if (dist1[c1[0]][c1[1]][i] != -1) {
                int[][][] dist2 = bfs(new int[] { c1[0], c1[1], i }, c2);
                int min = Integer.MAX_VALUE;
                for (int j = 0; j < 4; j++) {
                    if (dist2[c2[0]][c2[1]][j] != -1) {
                        min = Math.min(min, dist2[c2[0]][c2[1]][j]);
                    }
                }
                if (min != Integer.MAX_VALUE) {
                    ret = Math.min(ret, dist1[c1[0]][c1[1]][i] + min);
                }
            }
        }
        // S-C2-C1
        dist1 = bfs(start, c2);
        for (int i = 0; i < 4; i++) {
            if (dist1[c2[0]][c2[1]][i] != -1) {
                int[][][] dist2 = bfs(new int[] { c2[0], c2[1], i }, c1);
                int min = Integer.MAX_VALUE;
                for (int j = 0; j < 4; j++) {
                    if (dist2[c1[0]][c1[1]][j] != -1) {
                        min = Math.min(min, dist2[c1[0]][c1[1]][j]);
                    }
                }
                if (min != Integer.MAX_VALUE) {
                    ret = Math.min(ret, dist1[c2[0]][c2[1]][i] + min);
                }
            }
        }
        bw.append(ret == Integer.MAX_VALUE ? -1 : ret).append("\n");
        System.out.print(bw);
    }

}
