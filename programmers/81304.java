import java.util.*;

class Solution {
    static int N;
    static int[] isTrap;
    static int[] T;
    static ArrayList<ArrayList<Pair>> adj;
    static ArrayList<ArrayList<Pair>> adj2;
    
    static int[][] dijkstra(int src) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.offer(new Pair(src, 0, 0));
        int[][] dist = new int[N + 1][1 << T.length];
        for (int i = 0; i < N + 1; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
        dist[src][0] = 0;
        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            int here = p.num; int hereTrap = p.trap; int hereCost = p.cost;
            if (dist[here][hereTrap] < hereCost) continue;
            // 활성화 된 트랩인 경우, 
            if (isTrap[here] >= 0 && (hereTrap & (1 << isTrap[here])) > 0) {
                // 활성화되지 않은 역방향 인접한 정점들과
                for (Pair e : adj2.get(here)) if (isTrap[e.num] == -1 || (hereTrap & (1 << isTrap[e.num])) == 0) {
                    int there = e.num; int thereTrap = hereTrap ^ (isTrap[there] >= 0 ? (1 << isTrap[there]) : 0); int thereCost = hereCost + e.cost;
                    if (dist[there][thereTrap] > thereCost) {
                        dist[there][thereTrap] = thereCost;
                        pq.offer(new Pair(there, thereTrap, thereCost));
                    }
                }
                // 활성화 된 정방향 인접한 정점을 확인한다.
                for (Pair e : adj.get(here)) if (isTrap[e.num] >= 0 && (hereTrap & (1 << isTrap[e.num])) > 0) {
                    int there = e.num; int thereTrap = hereTrap ^ (1 << isTrap[there]); int thereCost = hereCost + e.cost;
                    if (dist[there][thereTrap] > thereCost) {
                        dist[there][thereTrap] = thereCost;
                        pq.offer(new Pair(there, thereTrap, thereCost));
                    }
                }
            // 활성화 된 트랩이 아닌 경우
            } else {
                // 활성화된 역방향 인접한 정점을 확인한다.
                for (Pair e : adj2.get(here)) if (isTrap[e.num] >= 0 && (hereTrap & (1 << isTrap[e.num])) > 0) {
                    int there = e.num; int thereTrap = hereTrap ^ (1 << isTrap[there]); int thereCost = hereCost + e.cost;
                    if (dist[there][thereTrap] > thereCost) {
                        dist[there][thereTrap] = thereCost;
                        pq.offer(new Pair(there, thereTrap, thereCost));
                    }
                }
                // 활성화되지 않은 정방향 인접한 정점을 확인한다.
                for (Pair e : adj.get(here)) if (isTrap[e.num] == -1 || (hereTrap & (1 << isTrap[e.num])) == 0) {
                    int there = e.num; int thereTrap = hereTrap ^ (isTrap[there] >= 0 ? (1 << isTrap[there]) : 0); int thereCost = hereCost + e.cost;
                    if (dist[there][thereTrap] > thereCost) {
                        dist[there][thereTrap] = thereCost;
                        pq.offer(new Pair(there, thereTrap, thereCost));
                    }
                }
            }
        }
        return dist;
    }
    
    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        N = n;
        isTrap = new int[N + 1]; Arrays.fill(isTrap, -1);
        for (int i = 0; i < traps.length; i++) isTrap[traps[i]] = i;
        T = traps;
        adj = new ArrayList<>(N); adj2 = new ArrayList<>(N);
        for (int i = 0; i <= N; i++) { adj.add(new ArrayList<>()); adj2.add(new ArrayList<>()); }
        for (int[] edge : roads) {
            int u = edge[0]; int v = edge[1]; int w = edge[2];
            adj.get(u).add(new Pair(v, w));
            adj2.get(v).add(new Pair(u, w));
        }
        int[][] dist = dijkstra(start);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < (1 << T.length); i++)
            min = Math.min(min, dist[end][i]);
        return min;
    }
    
}

class Pair implements Comparable<Pair> {
    int num, trap, cost;
    
    Pair (int n, int c) { num = n; cost = c; }
    
    Pair (int n, int t, int c) { num = n; trap = t; cost = c; }
    
    @Override
    public int compareTo(Pair o) { return cost - o.cost; }
}
