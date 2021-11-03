import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            int N = Integer.parseInt(br.readLine());
            DisjointSet S = new DisjointSet(N);
            String line = "";
            while (!(line = br.readLine()).equals("O")) {
                StringTokenizer st = new StringTokenizer(line, " ");
                String op = st.nextToken();
                int u = Integer.parseInt(st.nextToken());
                int v = 0;
                if (op.equals("E")) {
                    bw.append(S.find(u).value).append("\n");
                } else {
                    v = Integer.parseInt(st.nextToken());
                    S.union(u, v);
                }
            }
        }
        System.out.print(bw);
    }

}
class DisjointSet {
    int[] parent;
    int[] dist;

    DisjointSet(int N) {
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) parent[i] = i;
        dist = new int[N + 1];
    }

    // 서로 다른 클러스터에 있는 센터 u와 기업 v를 연결
    void union(int u, int v) {
        parent[u] = v;
        dist[u] = Math.abs(u - v) % 1000;
    }

    Pair find(int u) {
        if (parent[u] == u) return new Pair(u, 0);
        Pair p = find(parent[u]);
        parent[u] = p.index;
        dist[u] += p.value;
        return new Pair(parent[u], dist[u]);
    }
}
// 현재 정점이 속하는 클러스터의 센터의 번호와 그 센터까지의 거리
class Pair {
    int index, value;

    Pair(int i, int v) { index = i; value = v; }
}
