import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static char[][] S;
    static char[] C;

    static int[] I;
    static Queue<int[]> R;

    static final int[] dy = { 1, 1, 1, 0, 0, 0, -1, -1, -1 };
    static final int[] dx = { -1, 0, 1, -1, 0, 1, -1, 0, 1 };
    
    static boolean inRange(int y, int x) { return 0 <= y && y < N && 0 <= x && x < M; }

    // (y, x)에 위치한 미친 아두이노가 가야할 방향
    static int dir(int y, int x) {
        int min = dist(y, x); int dir = 4;
        for (int d = 0; d < 9; d++) if (d != 4) {
            int ny = y + dy[d]; int nx = x + dx[d];
            if (inRange(ny, nx) && min > dist(ny, nx)) {
                min = dist(ny, nx);
                dir = d;
            }
        }
        return dir;
    }

    // (y, x)에 위치한 미친 아두이노로부터 종수까지의 거리
    static int dist(int y, int x) {
        return Math.abs(I[0] - y) + Math.abs(I[1] - x);
    }

    // 게임을 한 턴 진행시킴, 게임이 끝났다면 false 반환
    static boolean game(int i) {
        int y = I[0]; int x = I[1];
        int[] p = move(y, x, C[i] - '0' - 1);
        int ny = p[0]; int nx = p[1];
        if (S[ny][nx] == 'R') return false;
        S[y][x] = '.';
        S[ny][nx] = 'I';
        I[0] = ny; I[1] = nx;
        if (!moveAll()) return false;
        return true;
    }

    static int[] move(int y, int x, int d) {
        int ny = y + dy[d]; int nx = x + dx[d];
        if (!inRange(ny, nx)) return null;
        return new int[] { ny, nx };
    }

    // 게임이 끝났다면 false 반환
    static boolean moveAll() {
        int size = R.size();
        int[][] cnt = new int[N][M];
        for (int i = 0; i < size; i++) {
            int[] p = R.poll();
            int y = p[0]; int x = p[1];
            S[y][x] = '.';
            int[] np = move(y, x, dir(y, x));
            int ny = np[0]; int nx = np[1];
            if (ny == I[0] && nx == I[1]) return false;
            cnt[ny][nx]++;
            R.offer(new int[] { ny, nx });
        }
        for (int i = 0; i < size; i++) {
            int[] p = R.poll();
            int y = p[0]; int x = p[1];
            if (cnt[y][x] < 2) {
                R.offer(p);
                S[y][x] = 'R';
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        S = new char[N][M];
        for (int i = 0; i < N; i++) {
            S[i] = br.readLine().toCharArray();
        }
        R = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (S[i][j] == 'I') {
                    I = new int[] { i, j };
                }
                if (S[i][j] == 'R') {
                    R.offer(new int[] { i, j });
                }
            }
        }
        C = br.readLine().toCharArray();
        boolean flag = true;
        for (int i = 0; i < C.length; i++) {
            if (!game(i)) {
                flag = false;
                bw.append(String.format("kraj %d\n", i + 1));
                break;
            }
        }
        if (flag) {
            for (int i = 0; i < N; i++) {
                bw.append(String.format("%s\n", String.valueOf(S[i])));
            }
        }
        System.out.print(bw);
    }

}
