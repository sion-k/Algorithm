import java.util.*;
import java.io.*;

public class Main {
    static int N, K;
    static ArrayList<ArrayList<Pair>> adj;

    static long dijkstra() {
        // (here, k, dist)
        PriorityQueue<Tuple> pq = new PriorityQueue<>();
        pq.offer(new Tuple(1, K, 0));
        long[][] dist = new long[N + 1][K + 1];
        for (int i = 0; i < N + 1; i++) Arrays.fill(dist[i], Long.MAX_VALUE);
        dist[1][K] = 0;
        while (!pq.isEmpty()) {
            Tuple t = pq.poll();
            int here = t.index; int k = t.k; long cost = t.value;
            if (dist[here][k] < cost) continue;
            for (Pair edge : adj.get(here)) {
                int there = edge.index; long thereCost = cost + edge.value;
                if (dist[there][k] > thereCost) {
                    dist[there][k] = thereCost;
                    pq.offer(new Tuple(there, k, thereCost));
                }
                // 포장할 수 있는 경우
                if (k > 0 && dist[there][k - 1] > cost) {
                    dist[there][k - 1] = cost;
                    pq.offer(new Tuple(there, k - 1, cost));
                }
            }
        }
        long min = Long.MAX_VALUE;
        for (int i = 0; i <= K; i++) min = Math.min(min, dist[N][i]);
        return min;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        adj = new ArrayList<>();
        for (int i = 0; i <= N; i++) adj.add(new ArrayList<>());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj.get(u).add(new Pair(v, w));
            adj.get(v).add(new Pair(u, w));
        }
        bw.append(dijkstra()).append("\n");
        System.out.print(bw);
    }

}
class Pair implements Comparable<Pair> {
    int index, value;

    Pair(int i, int v) { index = i; value = v; }

    @Override
    public int compareTo(Pair o) { return value - o.value; }
}

class Tuple implements Comparable<Tuple> {
    int index, k;
    long value;
    Tuple(int i, int k, long v) {
        index = i; this.k = k; value = v;
    }

    @Override
    public int compareTo(Tuple o) {
        return Long.compare(value, o.value);
    }
}