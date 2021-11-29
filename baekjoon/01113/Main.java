import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[][] S;
    static boolean[][] outside;

    static final int[] dy = { -1, 1, 0, 0 };
    static final int[] dx = { 0, 0, -1, 1 };

    static boolean inRange(int y, int x) { return 0 <= y && y < N + 2 && 0 <= x && x < M + 2; }

    // S[sy][sx]가 속하는 인접한 높이가 같은 컴포넌트를 구해서 반환
    static ArrayList<int[]> bfs(int sy, int sx) {
        ArrayList<int[]> ret = new ArrayList<>();
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { sy, sx });
        boolean[][] booked = new boolean[N + 2][M + 2];
        booked[sy][sx] = true;
        while (!q.isEmpty()) {
            int[] p = q.poll();
            ret.add(p);
            int y = p[0]; int x = p[1];
            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d]; int nx = x + dx[d];
                if (inRange(ny, nx) && S[ny][nx] == S[sy][sx] && !booked[ny][nx]) {
                    q.offer(new int[] { ny, nx });
                    booked[ny][nx] = true;
                }
            }
        }
        return ret;
    }

    // K높이의 모든 컴포넌트에 대해서 바깥과 인접했다면 outside[y][x] 갱신
    // 그렇지 않다면 그 컴포넌트 크기만큼 ret에 더해서 반환
    static int bfsAll(int K) {
        int ret = 0;
        for (int sy = 0; sy < N + 2; sy++) {
            for (int sx = 0; sx < M + 2; sx++) {
                if (S[sy][sx] == K && !outside[sy][sx]) {
                    ArrayList<int[]> component = bfs(sy, sx);
                    boolean flag = true;
                    for (int[] p : component) {
                        int y = p[0]; int x = p[1];
                        for (int d = 0; d < 4; d++) {
                            int ny = y + dy[d]; int nx = x + dx[d];
                            if (!inRange(ny, nx) || outside[ny][nx]) {
                                flag = false;
                                break;
                            }
                        }
                    }
                    if (flag) {
                        for (int[] p : component) {
                            int y = p[0]; int x = p[1];
                            S[y][x]++;
                        }
                        ret += component.size();
                    } else {
                        for (int[] p : component) {
                            int y = p[0]; int x = p[1];
                            outside[y][x] = true;
                        }
                    }
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        S = new int[N + 2][M + 2];
        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            for (int j = 0; j < line.length(); j++) {
                S[i][j + 1] = line.charAt(j) - '0';
            }
        }
        outside = new boolean[N + 2][M + 2];
        int ret = 0;
        for (int k = 0; k <= 8; k++) {
            ret += bfsAll(k);
        }
        bw.append(ret);
        System.out.println(bw);
    }

}
