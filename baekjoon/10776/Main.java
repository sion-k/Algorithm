import java.util.*;
import java.io.*;

public class Main {
    static int K, N;
    static int A, B;
    static ArrayList<ArrayList<Tuple>> adj;

    static int dijkstra() {
        PriorityQueue<Tuple> pq = new PriorityQueue<>();
        pq.offer(new Tuple(A, 0, K));
        // 현재 뗏목의 두께가 K이고, N번 정점에 있을 때 최단 거리
        int[][] dist = new int[K + 1][N + 1];
        for (int i = 0; i < K + 1; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
        dist[K][A] = 0;
        while (!pq.isEmpty()) {
            Tuple t = pq.poll();
            int here = t.num; int weight = t.weight; int cost = t.cost;
            if (dist[cost][here] < weight) continue;
            for (Tuple edge : adj.get(here)) {
                int there = edge.num; int thereWeight = weight + edge.weight;
                int thereCost = cost - edge.cost;
                if (thereCost <= 0) continue;
                if (dist[thereCost][there] > thereWeight) {
                    dist[thereCost][there] = thereWeight;
                    pq.offer(new Tuple(there, thereWeight, thereCost));
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= K; i++) min = Math.min(min, dist[i][B]);
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        adj = new ArrayList<>();
        for (int i = 0; i <= N; i++) adj.add(new ArrayList<>());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            adj.get(u).add(new Tuple(v, t, h));
            adj.get(v).add(new Tuple(u, t, h));
        }
        st = new StringTokenizer(br.readLine(), " ");
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        System.out.println(dijkstra());
    }

}
class Tuple implements Comparable<Tuple> {
    int num, weight, cost;
    
    Tuple(int n, int w, int c) {
        num = n; weight = w; cost = c;
    }
    
    @Override
    public int compareTo(Tuple o) { return weight - o.weight; }
}
