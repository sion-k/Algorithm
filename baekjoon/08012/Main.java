import java.util.*;
import java.io.*;

public class Main {
    static ArrayList<ArrayList<Integer>> adj;
    static int[] depth;
    static int[][] parent;

    static final int MAX = 14;
    
    static void dfs(int here) {
        for (int there : adj.get(here)) if (depth[there] == -1) {
            depth[there] = depth[here] + 1;
            parent[there][0] = here;
            dfs(there);        
        }
    }

    static int lca(int u, int v) {
        if (depth[u] < depth[v]) { int temp = u; u = v; v = temp; }
        for (int i = MAX; i >= 0; i--) {
            if (depth[u] - depth[v] >= (1 << i)) {
                u = parent[u][i];
            }
        }
        if (u == v) return u;
        for (int i = MAX; i >= 0; i--) {
            if (parent[u][i] != parent[v][i]) {
                u = parent[u][i];
                v = parent[v][i];
            }
        }
        return parent[u][0];
    }

    static int dist(int u, int v) {
        return depth[u] + depth[v] - 2 * depth[lca(u, v)];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        adj = new ArrayList<>(N);
        for (int i = 0; i <= N; i++) adj.add(new ArrayList<>());
        depth = new int[N + 1];
        Arrays.fill(depth, -1);
        depth[1] = 0;
        parent = new int[N + 1][MAX + 1];
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        dfs(1);
        for (int i = 1; i <= MAX; i++) {
            for (int j = 1; j <= N; j++) {
                parent[j][i] = parent[parent[j][i - 1]][i - 1];
            }
        }
        int sum = 0; int prev = 1;
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            int here = Integer.parseInt(br.readLine());
            sum += dist(prev, here);
            prev = here;
        }
        bw.append(sum).append("\n");
        System.out.print(bw);
    }

}
