import java.util.*;
import java.io.*;

public class Main {

    static long kruskal(int N, ArrayList<Edge> S) {
        DisjointSet ds = new DisjointSet(N);
        Collections.sort(S);
        long ret = 0;
        for (Edge e : S) {
            int u = ds.find(e.u); int v = ds.find(e.v);
            if (u != v) {
                ret += e.w;
                ds.union(u, v);
            }
        }
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            ArrayList<Edge> S = new ArrayList<>();
            for (int u = 1; u <= N - 1; u++) {
                st = new StringTokenizer(br.readLine(), " ");
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                S.add(new Edge(u, v, w));
                S.add(new Edge(v, u, w));
            }
            long ret = 0;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int u = Integer.parseInt(st.nextToken()); 
                int v = Integer.parseInt(st.nextToken()); 
                int w = Integer.parseInt(st.nextToken());
                S.add(new Edge(u, v, w));
                S.add(new Edge(v, u, w));
                ret ^= kruskal(N, S);
            }
            bw.append(ret).append("\n");
        }
        System.out.print(bw);
    }

}

class Edge implements Comparable<Edge> {
    int u, v, w;

    Edge(int u, int v, int w) {
        this.u = u; this.v = v; this.w = w;
    }

    public int compareTo(Edge o) { return w - o.w; }

    public String toString() { return String.format("(%d %d %d)", u, v, w); }
}

class DisjointSet {
    int[] p;

    DisjointSet(int n) {
        p = new int[n];
        for (int i = 0; i < n; i++) p[i] = i;
    }

    void union(int u, int v) {
        u = find(u); v = find(v);
        if (u == v) return;
        p[u] = v;
    }

    int find(int u) {
        if (p[u] == u) return u;
        return p[u] = find(p[u]);
    }
}
