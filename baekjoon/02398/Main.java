import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static ArrayList<ArrayList<Pair>> adj;
	static boolean[][] counted;
	static int cnt = 0;
	
	static int[] dijkstra(int src) {
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.offer(new Pair(src, 0));
		int[] dist = new int[N + 1];
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
	
	// here에서 거꾸로 올라가면서 어떤 최단 경로에 속하는 간선을 모두 세준다
	static void dfs(int[] dist, int here, int parent) {
		if (dist[here] == 0) return;
		for (Pair e : adj.get(here)) {
			if (e.num != parent && dist[e.num] + e.cost == dist[here]) {
				if (!counted[here][e.num]) {
					counted[here][e.num] = counted[e.num][here] = true; 
					cnt++;
				}
				dfs(dist, e.num, here);
				return;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		adj = new ArrayList<>(N);
		for (int i = 0; i <= N; i++) adj.add(new ArrayList<>());
		counted = new boolean[N + 1][N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adj.get(u).add(new Pair(v, w));
			adj.get(v).add(new Pair(u, w));
		}
		st = new StringTokenizer(br.readLine(), " ");
		int[] S = new int[3];
		int[][] dist = new int[3][];
		for (int i = 0; i < 3; i++) {
			S[i] = Integer.parseInt(st.nextToken());
			dist[i] = dijkstra(S[i]);
		}
		// 최적의 교차점 x를 찾는다.
		int x = 0; long best = Integer.MAX_VALUE;
		for (int i = 1; i <= N; i++) {
			long sum = 0;
			for (int j = 0; j < 3; j++) sum += dist[j][i];
			if (best > sum) {
				best = sum;
				x = i;
			}
		}
		int[] xDist = dijkstra(x);
		for (int i = 0; i < 3; i++) dfs(xDist, S[i], S[i]);
		bw.append(best).append(" ").append(cnt).append("\n");
		for (int i = 1; i <= N; i++)
			for (int j = i + 1; j <= N; j++)
				if (counted[i][j])
					bw.append(i).append(" ").append(j).append("\n");
		System.out.print(bw);
	}

}

class Pair implements Comparable<Pair> {
	int num, cost;
	
	Pair(int n, int c) { num = n; cost = c; }
	
	@Override
	public int compareTo(Pair o) { return cost - o.cost; }
	
}
