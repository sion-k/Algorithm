import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static ArrayList<ArrayList<Pair>> adj;

    static int prim() {
        int sum = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.offer(new Pair(0, 0));
        boolean[] picked = new boolean[N];
        for (int i = 0; i < N; i++) {
            while (!pq.isEmpty() && picked[pq.peek().num]) {
                pq.poll();
            }
            sum += pq.peek().cost;
            picked[pq.peek().num] = true;
            for (Pair there : adj.get(pq.peek().num)) pq.offer(there);
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        while (N != 0 && M != 0) {
            adj = new ArrayList<>();
            for (int i = 0; i < N; i++) adj.add(new ArrayList<>());
            int sum = 0;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                adj.get(u).add(new Pair(v, w));
                adj.get(v).add(new Pair(u, w));
                sum += w;
            }
            bw.append(sum - prim()).append("\n");
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
        }
        System.out.print(bw);
    }

}
class Pair implements Comparable<Pair> {
    int num, cost;

    Pair(int n, int c) { num = n; cost = c; }

    @Override
    public int compareTo(Pair o) { return cost - o.cost; }
}
