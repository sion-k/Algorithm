import java.util.*;
import java.io.*;

public class Main {
    static ArrayList<ArrayList<Pair>> adj;
    static int index = 1;
    static RMQ rmq;
    static int[] trip;
    static int[] tripPos;
    static int[] depth;

    static void pre(int here) {
        trip[index++] = here;
        for (Pair p : adj.get(here)) {
            int there = p.index;
            if (depth[there] == -1) {
                depth[there] = depth[here] + p.value;
                pre(there);
                trip[index++] = here;
            }
        }
    }

    // u와 v의 lca 정점 번호 반환
    static int lca(int u, int v) {
        u = tripPos[u]; v = tripPos[v];
        if (u > v) { int temp = u; u = v; v = temp; }
        return rmq.query(u, v);
    }

    static int dist(int u, int v) {
        return depth[u] + depth[v] - 2 * depth[lca(u, v)];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        adj = new ArrayList<>();
        trip = new int[2 * N];
        depth = new int[N + 1];
        Arrays.fill(depth, -1);
        depth[0] = Integer.MAX_VALUE;
        depth[1] = 0;
        for (int i = 0; i <= N; i++) adj.add(new ArrayList<>());
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj.get(u).add(new Pair(v, w));
            adj.get(v).add(new Pair(u, w));
        }
        pre(1);
        rmq = new RMQ();
        tripPos = new int[N + 1];
        for (int i = 1; i < trip.length; i++) {
            if (tripPos[trip[i]] == 0) {
                tripPos[trip[i]] = i;
            }
        }
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            bw.append(dist(u, v));
            bw.append("\n");
        }
        System.out.print(bw);
    }

}
class Pair {
    int index, value;

    Pair(int i, int v) { index = i; value = v; }
}
// 구간에서 가장 높이가 작은 정점의 번호를 반환
class RMQ {
    int n;
    int[] range;

    RMQ() {
        n = Main.trip.length;
        range = new int[4 * n];
        init(Main.trip, 1, 0, n - 1);
    }

    int init(int[] array, int node, int nodeLeft, int nodeRight) {
        if (nodeLeft == nodeRight) return range[node] = array[nodeLeft];
        int mid = (nodeLeft + nodeRight) / 2;
        int l = init(array, 2 * node, nodeLeft, mid);
        int r = init(array, 2 * node + 1, mid + 1, nodeRight);
        if (Main.depth[l] < Main.depth[r]) return range[node] = l;
        return range[node] = r;
    }

    int query(int left, int right) {
        return query(left, right, 1, 0, n - 1);
    }

    private int query(int left, int right, int node, int nodeLeft, int nodeRight) {
        if (right < nodeLeft || nodeRight < left) return 0;
        if (left <= nodeLeft && nodeRight <= right) return range[node];
        int mid = (nodeLeft + nodeRight) / 2;
        int l = query(left, right, 2 * node, nodeLeft, mid);
        int r = query(left, right, 2 * node + 1, mid + 1, nodeRight);
        if (Main.depth[l] < Main.depth[r]) return l;
        return r;
    }
}
