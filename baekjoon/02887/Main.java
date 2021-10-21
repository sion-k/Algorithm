import java.util.*;
import java.io.*;

public class Main {

    static long kruskal(int N, ArrayList<Edge> S) {
        long sum = 0;
        Collections.sort(S);
        DisjointSet ds = new DisjointSet(N);
        for (Edge e : S) {
            int u = e.start; int v = e.end; int w = e.weight;
            if (ds.find(u) == ds.find(v)) continue;
            ds.union(u, v);
            sum += w;
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Pair> X = new ArrayList<>();
        ArrayList<Pair> Y = new ArrayList<>();
        ArrayList<Pair> Z = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            X.add(new Pair(i, Integer.parseInt(st.nextToken())));
            Y.add(new Pair(i, Integer.parseInt(st.nextToken())));
            Z.add(new Pair(i, Integer.parseInt(st.nextToken())));
        }
        Collections.sort(X);
        Collections.sort(Y);
        Collections.sort(Z);
        ArrayList<Edge> S = new ArrayList<>();
        for (int i = 1; i < X.size(); i++) {
            int u = X.get(i - 1).index; int v = X.get(i).index;
            int w = Math.abs(X.get(i).value - X.get(i - 1).value);
            S.add(new Edge(u, v, w));
            S.add(new Edge(v, u, w));
        }
        for (int i = 1; i < Y.size(); i++) {
            int u = Y.get(i - 1).index; int v = Y.get(i).index;
            int w = Math.abs(Y.get(i).value - Y.get(i - 1).value);
            S.add(new Edge(u, v, w));
            S.add(new Edge(v, u, w));
        }
        for (int i = 1; i < Z.size(); i++) {
            int u = Z.get(i - 1).index; int v = Z.get(i).index;
            int w = Math.abs(Z.get(i).value - Z.get(i - 1).value);
            S.add(new Edge(u, v, w));
            S.add(new Edge(v, u, w));
        }
        System.out.println(kruskal(N, S));
    }

}
class Pair implements Comparable<Pair> {
    int index, value;

    Pair(int i, int v) { index = i; value = v; }

    @Override
    public int compareTo(Pair o) { return value - o.value; }
}

class Edge implements Comparable<Edge> {
    int start, end, weight;

    Edge(int s, int e, int w) {
        start = s; end = e; weight = w;
    }

    @Override
    public int compareTo(Edge o) {
        return weight - o.weight;
    }
}

class DisjointSet {
    int[] parent;
    int[] rank;

    DisjointSet(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        rank = new int[n];
    }

    void union(int u, int v) {
        u = find(u); v = find(v);
        if (u == v) return;
        if (u > v) { int temp = u; u = v; v = temp; }
        parent[u] = v;
        if (rank[u] == rank[v]) rank[v]++;
    }

    int find(int u) {
        if (parent[u] == u) return u;
        return parent[u] = find(parent[u]);
    }
}
