import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static ArrayList<ArrayList<Integer>> adj;

    static int[] size;
    static int[] depth;
    static int[] parent;

    static long comb(int n) {
        return 1L * n * (n - 1) / 2;
    }

    static void dfs(int here, int prev) {
        size[here] = 1; depth[here] = depth[prev] + 1; parent[here] = prev;
        for (int there : adj.get(here)) if (there != prev) {
            dfs(there, here);
            size[here] += size[there];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        adj = new ArrayList<>();
        for (int i = 0; i <= N; i++) adj.add(new ArrayList<>());
        size = new int[N + 1];
        depth = new int[N + 1];
        parent = new int[N + 1];
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        depth[0] = -1;
        dfs(1, 0);
        long ret = 0;
        for (int p = 1; p <= N; p++) {
            long temp = comb(size[p]);
            for (int c : adj.get(p)) if (parent[c] == p) {
                temp -= comb(size[c]);
            }
            ret += temp * depth[p];
        }
        for (int c = 1; c <= N; c++) {
            ret += 1L * size[c] * (N - size[c]);
        }
        bw.append(ret).append("\n");
        System.out.print(bw);
    }

}
