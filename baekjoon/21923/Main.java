import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[][] S;
    static int[][][] cache;

    static final int INF = 987654321;
    // k = 0 : 상승 비행, k = 1 : 하강 비행
    // (y, x)에서 시작해서 (N, M)까지 최대 비행 점수
    static int dp(int k, int y, int x) {
        if (k == 1 && y == N - 1 && x == M - 1) return S[y][x];
        if (cache[k][y][x] != -INF) return cache[k][y][x];
        int max = -INF;
        // 앞으로 이동하는 경우
        if (x + 1 < M) max = Math.max(max, dp(k, y, x + 1));
        // 상승 비행
        if (k == 0) {
            // 하강 비행으로 전환
            max = Math.max(max, dp(1, y, x));
            // 상승 비행
            if (y - 1 >= 0) max = Math.max(max, dp(k, y - 1, x));
        // 하강 비행
        } else if (y + 1 < N) {
            max = Math.max(max, dp(k, y + 1, x));
        }
        return cache[k][y][x] = S[y][x] + max;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        S = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) S[i][j] = Integer.parseInt(st.nextToken());
        }
        cache = new int[2][N][M];
        for (int k = 0; k < 2; k++) {
            for (int i = 0; i < N; i++) {
                Arrays.fill(cache[k][i], -INF);
            }
        }
        bw.append(dp(0, N - 1, 0));
        System.out.println(bw);
    }

}
