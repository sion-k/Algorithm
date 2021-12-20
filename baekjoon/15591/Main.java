import java.util.*;
import java.io.*;

public class Main {
    static ArrayList<ArrayList<Pair>> adj;
    static int[] S;

    static final int INF = 1000000000;

    static void dfs(int here, int prev, int path) {
        S[here] = path;
        for (Pair e : adj.get(here)) if (e.index != prev) {
            dfs(e.index, here, Math.min(path, e.value));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        adj = new ArrayList<>();
        for (int i = 0; i <= N; i++) adj.add(new ArrayList<>());
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj.get(u).add(new Pair(v, w));
            adj.get(v).add(new Pair(u, w));
        }
        S = new int[N + 1];
        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine(), " ");
            int k = Integer.parseInt(st.nextToken());
            int here = Integer.parseInt(st.nextToken());
            dfs(here, here, INF);
            int cnt = 0;
            for (int i = 1; i <= N; i++) {
                if (i == here) continue;
                if (S[i] >= k) cnt++;
            }
            bw.append(cnt).append("\n");
        }
        System.out.print(bw);
    }

}
class Pair {
    int index, value;

    Pair(int i, int v) { index = i; value = v; }
}
