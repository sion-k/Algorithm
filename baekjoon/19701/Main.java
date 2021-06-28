import java.util.*;
import java.io.*;

public class Main {
	static int V;
	static ArrayList<ArrayList<Pair>> adj;
	
	static int[] dijkstra(int src) {
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.offer(new Pair(src, 0));
		int[] dist = new int[2 * V + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[src] = 0;
		while (!pq.isEmpty()) {
			Pair p = pq.poll();
			int here = p.num; int hereCost = p.cost;
			if (dist[here] < hereCost) continue;
			for (Pair e : adj.get(here)) {
				int there = e.num; int thereCost = hereCost + e.cost;
				if (dist[there] > thereCost) {
					dist[there] = thereCost;
					pq.offer(new Pair(there, thereCost));
				}
			}
		}
		return dist;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		// [1, V] = 지금까지 돈까스를 먹지 않았을 때
		// [V + 1, 2 * V] = 돈까스를 이미 먹은 경우
		V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		adj = new ArrayList<>(2 * V + 1);
		for (int i = 0; i <= 2 * V + 1; i++) adj.add(new ArrayList<>());
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			// 돈까스를 먹지 않고 이동하는 간선
			adj.get(x).add(new Pair(y, t));
			adj.get(y).add(new Pair(x, t));
			adj.get(V + x).add(new Pair(V + y, t));
			adj.get(V + y).add(new Pair(V + x, t));
			// 돈까스를 먹는 간선
			adj.get(x).add(new Pair(V + y, t - k));
			adj.get(y).add(new Pair(V + x, t - k));
		}
		int[] dist = dijkstra(1);
		for (int i = V + 2; i <= 2 * V; i++)
			bw.append(dist[i]).append("\n");
		System.out.print(bw);
	}
	
}
class Pair implements Comparable<Pair> {
	int num; int cost;
	
	Pair(int n, int c) { num = n; cost = c; }
	
	@Override
	public int compareTo(Pair o) { return cost - o.cost; }
	
}
