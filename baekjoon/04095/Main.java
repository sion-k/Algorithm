import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static boolean[][] S;
    static int[][] cache;

    static final int INF = 987654321;

    static final int[] dy = { 0, 1, 1 };
    static final int[] dx = { 1, 1, 0 };

    static boolean inRange(int y, int x) { return 0 <= y && y < N && 0 <= x && x < M; }

    // (y, x)를 왼쪽 위로 하는 가장 큰 정사각형의 한 변 길이
    static int dp(int y, int x) {
        if (!S[y][x]) return 0;
        if (cache[y][x] != -1) return cache[y][x];
        int min = INF;
        int cnt = 0;
        for (int d = 0; d < 3; d++) {
            int ny = y + dy[d]; int nx = x + dx[d];
            if (inRange(ny, nx)) {
                min = Math.min(min, dp(ny, nx));
                cnt++;
            }
        }
        if (cnt != 3) min = 0;
        return cache[y][x] = min + 1;
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
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < M; j++) {
                    S[i][j] = st.nextToken().equals("1");
                }
            }
            cache = new int[N][M];
            for (int i = 0; i < N; i++) Arrays.fill(cache[i], -1);
            int max = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    max = Math.max(max, dp(i, j));
                }
            }
            bw.append(max).append("\n");
        }
        System.out.print(bw);
    }

}
