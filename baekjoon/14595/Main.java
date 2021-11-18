import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        DisjointSet s = new DisjointSet(N + 1);
        ArrayList<Pair> P = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            P.add(new Pair(x, y));
        }
        Collections.shuffle(P);
        for (int i = 0; i < M; i++) {
            int x = P.get(i).x; int y = P.get(i).y;
            for (int j = x; j < y; j++) {
                if (s.find(j) != s.find(j + 1)) {
                    s.union(j, j + 1);
                    N--;
                }
                if (s.find(x) == s.find(y)) break;
            }
        }
        bw.append(N);
        System.out.println(bw);
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
        if (rank[u] > rank[v]) { int temp = u; u = v; v = temp; }
        parent[u] = v;
        if (rank[u] == rank[v]) rank[v]++;
    }

    int find(int u) {
        if (parent[u] == u) return u;
        return parent[u] = find(parent[u]);
    }

}
class Pair {
    int x, y;

    Pair(int x, int y) {
        this.x = x; this.y = y;
    }
}