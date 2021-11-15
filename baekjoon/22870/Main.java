import java.util.*;
import java.util.LinkedList;
import java.io.*;

public class Main {
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i <= N; i++) adj.add(new ArrayList<>()); 
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj.get(u).add(new Pair(v, w));
            adj.get(v).add(new Pair(u, w));
        }
        st = new StringTokenizer(br.readLine(), " ");
        int S = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        // E에서 S까지의 최단 경로를 담은 배열 구성
        // E에서 S로 반대로 다익스트라를 적용시키면
        // S에서 E로 가면서 S[here] - cost = S[there]인 경우
        // there이 최단 경로에 속하는 정점임을 쉽게 알 수 있음
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.offer(new Pair(E, 0));
        int[] dist1 = new int[N + 1];
        Arrays.fill(dist1, INF);
        dist1[E] = 0;
        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            int here = p.index; int cost = p.value;
            if (dist1[here] < cost) continue;
            for (Pair e : adj.get(here)) {
                int there = e.index; int thereCost = cost + e.value;
                if (dist1[there] > thereCost) {
                    dist1[there] = thereCost;
                    pq.offer(new Pair(there, thereCost));
                }
            }
        }
        // S-E 최단 거리
        int sum = dist1[S];
        // BFS를 통해 S에서 E까지 최단경로이면서 사전순으로 가장 작은 경로를 찾음
        Queue<Integer> q = new LinkedList<>();
        q.offer(S);
        boolean[] booked = new boolean[N + 1];
        booked[S] = true;
        // E에서 다시 S로 돌아오면서
        // 사전순으로 가장 앞선 최단 경로의 역추적을 위한 배열
        int[] prev = new int[N + 1];
        Arrays.fill(prev, -1);
        // 실제 최단경로의 역추적 과정에서는 절대로 사이클이 나올 수 없음
        // 따라서 종료조건은 S = prev[S]인경우
        prev[S] = S;
        while (!q.isEmpty()) {
            int here = q.poll();
            int min = N + 1;
            for (Pair e : adj.get(here)) {
                int there = e.index; int cost = e.value;
                if (!booked[there] && dist1[here] - cost == dist1[there]) {
                    min = Math.min(min, there);
                }
            }
            if (min == N + 1) break;
            q.offer(min);
            booked[min] = true;
            prev[min] = here;
        }
        boolean[] used = new boolean[N + 1];
        int t = prev[E];
        // S에 도착할 때 까지 반복
        while (prev[t] != t) {
            used[t] = true;
            t = prev[t];
        }
        // E에서 S까지의 최단 경로를 담은 배열 구성
        pq = new PriorityQueue<>();
        pq.offer(new Pair(E, 0));
        int[] dist2 = new int[N + 1];
        Arrays.fill(dist2, INF);
        dist2[E] = 0;
        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            int here = p.index; int cost = p.value;
            if (dist2[here] < cost) continue;
            for (Pair e : adj.get(here)) {
                int there = e.index; int thereCost = cost + e.value;
                if (!used[there] && dist2[there] > thereCost) {
                    dist2[there] = thereCost;
                    pq.offer(new Pair(there, thereCost));
                }
            }
        }
        sum += dist2[S];
        bw.append(sum);
        System.out.println(bw);
    }

}
class Pair implements Comparable<Pair> {
    int index, value;

    Pair(int i, int v) { index = i; value = v; }

    @Override
    public int compareTo(Pair o) { return value - o.value; }
}
