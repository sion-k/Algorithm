import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        boolean[][] R = new boolean[N][N];
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>(N);
        for (int i = 0; i < N; i++) adj.add(new ArrayList<>());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj.get(u).add(new Pair(v, w));
            R[u][v] = true;
        }
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    R[i][j] = R[i][j] || (R[i][k] && R[k][j]);
                }
            }
        }
        int[] S = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) S[i] = -Integer.parseInt(st.nextToken());
        long[] dist = new long[N];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[start] = S[start];
        boolean flag = false;
        // N번 완화
        for (int k = 1; k <= N; k++) {
            for (int here = 0; here < N; here++) {
                if (dist[here] == Long.MAX_VALUE) continue;
                for (Pair e : adj.get(here)) {
                    int there = e.index; long cost = dist[here] + S[there] + e.value;
                    if (dist[there] > cost) {
                        dist[there] = cost;
                        if (k == N && R[here][end]) flag = true;
                    }
                }
            }
        }
        if (dist[end] == Long.MAX_VALUE) {
            bw.append("gg");
        } else if (flag) {
            bw.append("Gee");
        } else {
            bw.append(-dist[end]);
        }
        System.out.println(bw);
    }

}
class Pair {
    int index, value;

    Pair(int i, int v) { index = i; value = v; }
}
