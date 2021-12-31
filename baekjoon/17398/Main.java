import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] S1 = new int[M][2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            S1[i][0] = Integer.parseInt(st.nextToken());
            S1[i][1] = Integer.parseInt(st.nextToken());
        }
        // 쿼리
        int[] Q = new int[K];
        // 제거된 간선들
        boolean[] S2 = new boolean[M];
        for (int i = 0; i < K; i++) {
            Q[i] = Integer.parseInt(br.readLine()) - 1;
            S2[Q[i]] = true;
        }
        DisjointSet ds = new DisjointSet(N + 1);
        // 제거되지 않은 간선 모두 연결
        for (int i = 0; i < M; i++) if (!S2[i]) {
            int u = S1[i][0]; int v = S1[i][1];
            // bw.append(String.format("union : %d %d\n", u, v));
            ds.union(u, v);
        }
        long ret = 0;
        // 쿼리 거꾸로
        for (int i = K - 1; i >= 0; i--) {
            int u = S1[Q[i]][0]; int v = S1[Q[i]][1];
            long temp = ds.union(u, v);
            ret += temp;
            // bw.append(String.format("%d : %d %d = %d\n", Q[i], u, v, temp));
        }
        bw.append(ret);
        System.out.print(bw);
    }

}
class DisjointSet {
    int[] p;

    DisjointSet(int n) {
        p = new int[n];
        Arrays.fill(p, -1);
    }

    long union(int u, int v) {
        u = find(u); v = find(v);
        if (u == v) return 0;
        long ret = (long)(p[u]) * p[v];
        p[u] += p[v];
        p[v] = u;
        return ret;
    }

    int find(int u) {
        if (p[u] < 0) return u;
        return p[u] = find(p[u]);
    }
}
