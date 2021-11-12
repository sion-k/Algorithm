import java.util.*;
import java.io.*;

public class Main {
    static ArrayList<ArrayList<Pair>> adj;
    static Pair[][] parent;

    static final int MAX = 16;

    static void dfs(int here) {
        for (Pair p : adj.get(here)) {
            int there = p.index;
            if (parent[there][0] == null && there != 1) {
                parent[there][0] = new Pair(here, p.value);
                dfs(there);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] S = new int[N + 1];
        for (int i = 1; i <= N; i++) S[i] = Integer.parseInt(br.readLine());
        adj = new ArrayList<>();
        for (int i = 0; i <= N; i++) adj.add(new ArrayList<>());
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj.get(u).add(new Pair(v, w));
            adj.get(v).add(new Pair(u, w));
        }
        parent = new Pair[N + 1][MAX + 1];
        dfs(1);
        // f(i, j) = i번째 정점에서 2^j번 올라가면 도달하는 정점과 필요한 비용
        for (int j = 1; j <= MAX; j++) {
            for (int i = 1; i <= N; i++) {
                Pair p = parent[i][j - 1];
                if (p == null) continue;
                Pair q = parent[p.index][j - 1];
                if (q == null) continue;
                parent[i][j] = new Pair(q.index, p.value + q.value);
            }
        }
        for (int i = 1; i <= N; i++) {
            int t = i;
            for (int j = MAX; j >= 0; j--) {
                if (parent[t][j] != null && S[i] - parent[t][j].value >= 0) {
                    S[i] -= parent[t][j].value;
                    t = parent[t][j].index;
                }
            }
            bw.append(t).append("\n");
        }
        System.out.print(bw);
    }

}
class Pair {
    int index, value;

    Pair(int i, int v) { index = i; value = v; }

    @Override
    public String toString() { return String.format("(%d, %d)", index, value); }
}
