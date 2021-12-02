import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static ArrayList<ArrayList<Pair>> adj;

    static final int INF = 987654321;

    static int[] dijkstra() {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.offer(new Pair(1, 0));
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;
        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            int here = p.index; int cost = p.value;
            if (dist[here] < cost) continue;
            for (Pair e : adj.get(here)) {
                int there = e.index; int thereCost = cost + e.value;
                if (dist[there] > thereCost) {
                    dist[there] = thereCost;
                    pq.offer(new Pair(there, thereCost));
                }
            }
        }
        return dist;
    }

    static int[][] dijkstra2() {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        // here, k, cost
        // k = 0 두 배의 속도로 달림 k = 1 절반의 속도로 걸어감
        pq.offer(new Pair(1, 0, 0));
        int[][] dist = new int[2][N + 1];
        Arrays.fill(dist[0], INF);
        Arrays.fill(dist[1], INF);
        dist[0][1] = 0;
        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            int here = p.index; int k = p.k; int cost = p.value;
            if (dist[k][here] < cost) continue;
            for (Pair e : adj.get(here)) {
                int there = e.index; int thereCost = cost;
                if (k == 0) thereCost += e.value / 2;
                else thereCost += e.value * 2;
                if (dist[k ^ 1][there] > thereCost) {
                    dist[k ^ 1][there] = thereCost;
                    pq.offer(new Pair(there, k ^ 1, thereCost));
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        adj = new ArrayList<>(N);
        for (int i = 0; i <= N; i++) adj.add(new ArrayList<>());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj.get(u).add(new Pair(v, 2 * w));
            adj.get(v).add(new Pair(u, 2 * w));
        }
        int[] dist1 = dijkstra();
        int[][] dist2 = dijkstra2();
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            if (dist1[i] < Math.min(dist2[0][i], dist2[1][i])) {
                cnt++;
            }
        }
        bw.append(cnt);
        System.out.println(bw);
    }

}
class Pair implements Comparable<Pair> {
    int index, k, value;

    Pair(int i, int v) { index = i; value = v; }

    Pair(int i, int k, int v) {
        this(i, v);
        this.k = k;
    }

    @Override
    public int compareTo(Pair o) { return value - o.value; }
}
