import java.util.*;
import java.io.*;

public class Main {
    static ArrayList<ArrayList<Integer>> adj;

    static int[] depth;
    static int[][] parent;

    static final int MAX = 16;

    static void dfs(int here) {
        for (int there : adj.get(here)) if (depth[there] == -1) {
            depth[there] = depth[here] + 1;
            parent[there][0] = here;
            dfs(there);
        }
    }

    static int lca(int u, int v) {
        if (depth[u] < depth[v]) { int temp = u; u = v; v = temp; }
        int diff = depth[u] - depth[v];
        int i = 0;
        while (diff > 0) {
            if (diff % 2 == 1) u = parent[u][i];
            diff >>= 1;
            i++;
        }
        if (u != v) {
            for (int j = MAX; j >= 0; j--) {
                if (parent[u][j] != -1 && parent[u][j] != parent[v][j]) {
                    u = parent[u][j];
                    v = parent[v][j];
                }
            }
            u = parent[u][0];
        }
        return u;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        depth = new int[N + 1];
        Arrays.fill(depth, -1);
        depth[1] = 0;
        parent = new int[N + 1][MAX + 1];
        adj = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(parent[i], -1);
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        dfs(1);
        for (int k = 1; k <= MAX; k++) {
            for (int i = 1; i <= N; i++) {
                if (parent[i][k - 1] != -1) {
                    parent[i][k] = parent[parent[i][k - 1]][k - 1];
                }
            }
        }
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            bw.append(lca(u, v));
            bw.append("\n");
        }
        System.out.print(bw);
    }

}
