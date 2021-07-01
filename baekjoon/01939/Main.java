import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static ArrayList<ArrayList<Pair>> adj;
	
	static int INF = 1000000000;
	
	// 가장 중량 제한이 큰 경로부터 찾는다
	static int[] dijkstra(int start) {
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.offer(new Pair(start, INF));
		int[] dist = new int[N + 1];
		while (!pq.isEmpty()) {
			Pair p = pq.poll();
			int here = p.num; int hereCost = p.cost;
			if (dist[here] > hereCost) continue;
			for (Pair edge : adj.get(here)) {
				int there = edge.num; int thereCost = Math.min(hereCost, edge.cost);
				if (dist[there] < thereCost) {
					dist[there] = thereCost;
					pq.offer(new Pair(there, thereCost));
				}
			}
		}
		return dist;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		adj = new ArrayList<>(N);
		for (int i = 0; i <= N; i++) adj.add(new ArrayList<>());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			adj.get(A).add(new Pair(B, C));
			adj.get(B).add(new Pair(A, C));
		}
		st = new StringTokenizer(br.readLine(), " ");
		int u = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());
		System.out.println(dijkstra(u)[v]);
	}

}

class Pair implements Comparable<Pair> {
	int num, cost;
	
	Pair (int n, int c) { num = n; cost = c; }
	
	@Override
	public int compareTo(Pair o) { return o.cost - cost; }
	
}
