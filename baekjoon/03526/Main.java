import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static ArrayList<ArrayList<Pair>> adj;
    static int[] dist;

    static void bfs(StringBuilder bw) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(N);
        dist = new int[N + 1];
        Arrays.fill(dist, -1);
        dist[N] = 0;
        while (!q.isEmpty()) {
            int here = q.poll();
            for (Pair p : adj.get(here)) if (dist[p.index] == -1) {
                int there = p.index;
                q.offer(there);
                dist[there] = dist[here] + 1;
            }
        }
        bw.append(dist[1]).append("\n");
    }

    static void construct(StringBuilder bw) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        boolean[] booked = new boolean[N + 1];
        booked[1] = true;
        while (!q.isEmpty()) {
            int size = q.size();
            // 사전순으로 가장 작은 가중치를 구한다
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < size; i++) {
                int here = q.poll();
                for (Pair p : adj.get(here)) {
                    int there = p.index;
                    if (dist[here] - 1 == dist[there]) {
                        min = Math.min(min, p.value);
                    }
                }
                q.offer(here);
            }
            if (min != Integer.MAX_VALUE) bw.append(min).append(" ");
            // 사전순으로 가장 작은 가중치를 갖는 간선이 여러개라면 모두 방문
            for (int i = 0; i < size; i++) {
                int here = q.poll();
                for (Pair p : adj.get(here)) {
                    int there = p.index;
                    if (p.value == min && !booked[there] && dist[here] - 1 == dist[there]) {
                        q.offer(there);
                        booked[there] = true;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        adj = new ArrayList<>();
        for (int i = 0; i <= N; i++) adj.add(new ArrayList<>());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj.get(a).add(new Pair(b, c));
            adj.get(b).add(new Pair(a, c));
        }
        for (int i = 0; i <= N; i++) Collections.sort(adj.get(i));
        bfs(bw);
        construct(bw);
        System.out.println(bw);
    }

}
class Pair implements Comparable<Pair> {
    int index, value;

    Pair(int i, int v) { index = i; value = v; }

    @Override
    public int compareTo(Pair o) { return value - o.value; }
}
