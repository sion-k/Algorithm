import java.util.*;
import java.io.*;

public class Main {

    static final int[] dx = { -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<Set<Integer>> adj = new ArrayList<>(N);
        for (int i = 0; i <= N; i++) adj.add(new HashSet<>());
        int[][] S = new int[M][K];
        Deque<int[]> q = new LinkedList<>();
        int[][] dist = new int[M][K];
        for (int i = 0; i < M; i++) Arrays.fill(dist[i], -1);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < K; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
                adj.get(S[i][j]).add(i);
                if (S[i][j] == 1) {
                    q.offer(new int[] { i, j });
                    dist[i][j] = 0;
                }
            }
        }
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int y = p[0]; int x = p[1];
            // 하이퍼 루프를 따라 양 옆으로 이동하는 경우
            for (int d = 0; d < 2; d++) {
                int nx = x + dx[d];
                if (0 <= nx && nx < K && dist[y][nx] == -1) {
                    q.offerFirst(new int[] { y, nx });
                    dist[y][nx] = dist[y][x];
                }
            }
            // 다른 하이퍼루프로 환승하는 경우
            for (int there : adj.get(S[y][x])) if (dist[there][0] == -1) {
                q.offerLast(new int[] { there, 0 });
                dist[there][0] = dist[y][x] + 1;
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < K; j++) {
                if (S[i][j] == N) {
                    if (dist[i][j] != -1) {
                        min = Math.min(min, dist[i][j]);
                    }
                }
            }
        }
        if (min == Integer.MAX_VALUE) min = -1;
        else if (N != 1) min += 2;
        else min = 1;
        bw.append(min).append("\n");
        System.out.print(bw);
    }

}
