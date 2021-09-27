import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static ArrayList<ArrayList<Pair>> adj;

    static long f(int A, int B, long C, int D) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.offer(new Pair(A, 0));
        long[] dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[A] = 0;
        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            int here = p.index; long hereCost = p.value;
            if (dist[here] < hereCost) continue;
            for (Pair e : adj.get(here)) if (e.value <= D) {
                int there = e.index; long thereCost = hereCost + e.value;
                if (dist[there] > thereCost && thereCost <= C) {
                    dist[there] = thereCost;
                    pq.offer(new Pair(there, thereCost));
                }
            }
        }
        return dist[B];
    }

    static int solve(int A, int B, long C) {
        int lo = 0; int hi = 1000000000;
        if (f(A, B, C, hi) > C) return -1;
        while (lo + 1 < hi) {
            int mid = (lo + hi) / 2;
            if (f(A, B, C, mid) <= C) hi = mid;
            else lo = mid;
        }
        return hi;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        long C = Long.parseLong(st.nextToken());
        adj = new ArrayList<>();
        for (int i = 0; i <= N; i++) adj.add(new ArrayList<>());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj.get(u).add(new Pair(v, c));
            adj.get(v).add(new Pair(u, c));
        }
        System.out.println(solve(A, B, C));
    }

}
class Pair implements Comparable<Pair> {
    int index; long value;

    Pair(int i, long v) { index = i; value = v; }

    @Override
    public int compareTo(Pair o) { 
        return value == o.value ? 0 : (value > o.value ? 1 : -1);
    }
}
