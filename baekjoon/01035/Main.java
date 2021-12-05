import java.util.*;
import java.io.*;

public class Main {
    static int[][] P;

    static final int[] dy = { -1, 1, 0, 0 };
    static final int[] dx = { 0, 0, -1, 1 };

    static final int INF = 987654321;

    static boolean inRange(int y, int x) { return 0 <= y && y < 5 && 0 <= x && x < 5; }

    // 이미 조각이 배치되었는지 여부 S
    static int[][] dist(boolean[][] S, int sy, int sx) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { sy, sx });
        int[][] dist = new int[5][5];
        for (int i = 0; i < 5; i++) Arrays.fill(dist[i], -1);
        dist[sy][sx] = 0;
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int y = p[0]; int x = p[1];
            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d]; int nx = x + dx[d];
                if (inRange(ny, nx) && !S[ny][nx] && dist[ny][nx] == -1) {
                    q.offer(new int[] { ny, nx });
                    dist[ny][nx] = dist[y][x] + 1;
                }
            }
        }
        return dist;
    }

    static boolean dfsAll(boolean[][] S) {
        int cnt = 0;
        boolean[][] visit = new boolean[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (S[i][j] && !visit[i][j]) {
                    dfs(S, visit, i, j);
                    cnt++;
                    if (cnt == 2) return false;
                }
            }
        }
        return true;
    }

    static void dfs(boolean[][] S, boolean[][] visit, int y, int x) {
        visit[y][x] = true;
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d]; int nx = x + dx[d];
            if (inRange(ny, nx) && S[ny][nx] && !visit[ny][nx]) {
                dfs(S, visit, ny, nx);
            }
        }
    }
    
    // 이미 조각이 배치되었는지 여부 S
    // i번째 조각부터 이동시킬 때 하나의 컴포넌트를 이루는 최단 거리
    static int btk(boolean[][] S, int i) {
        if (i == 5 || P[i] == null) return dfsAll(S) ? 0 : INF;
        int min = btk(S, i + 1);
        int y = P[i][0]; int x = P[i][1];
        int[][] dist = dist(S, y, x);
        for (int ny = 0; ny < 5; ny++) {
            for (int nx = 0; nx < 5; nx++) {
                if (!S[ny][nx] && dist[ny][nx] != -1) {
                    S[y][x] = false;
                    S[ny][nx] = true;
                    min = Math.min(min, btk(S, i + 1) + dist[ny][nx]);
                    S[y][x] = true;
                    S[ny][nx] = false;
                }
            }
        }
        return min;
    }
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        int k = 0;
        boolean[][] S = new boolean[5][5];
        P = new int[5][];
        for (int i = 0; i < 5; i++) {
            String line = br.readLine();
            for (int j = 0; j < 5; j++) {
                if (line.charAt(j) == '*') {
                    S[i][j] = true;
                    P[k++] = new int[] { i, j };
                }
            }
        }
        bw.append(btk(S, 0));
        System.out.println(bw);
    }

}
