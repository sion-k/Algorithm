import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static ArrayList<ArrayList<Pair>> adj;
	static ArrayList<ArrayList<Pair>> adj2; // 반대 간선
	static final int INF = 100001;

	static int[] dijkstra(int src) {
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.offer(new Pair(src, 0));
		int[] dist = new int[N + 1];
		Arrays.fill(dist, INF);
		dist[src] = 0;
		while (!pq.isEmpty()) {
			Pair p = pq.poll();
			int here = p.num; int cost = p.cost;
			if (dist[here] < cost) {continue;}
			for (Pair edge : adj.get(here)) {
				int there = edge.num;
				int nextDist = cost + edge.cost;
				if (dist[there] > nextDist) {
					dist[there] = nextDist;
					pq.offer(new Pair(there, nextDist));
				}
			}
		}
		return dist;
	}

	static int[] dijkstra2(int src) {
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.offer(new Pair(src, 0));
		int[] dist = new int[N + 1];
		Arrays.fill(dist, INF);
		dist[src] = 0;
		while (!pq.isEmpty()) {
			Pair p = pq.poll();
			int here = p.num; int cost = p.cost;
			if (dist[here] < cost) {continue;}
			for (Pair edge : adj2.get(here)) {
				int there = edge.num;
				int nextDist = cost + edge.cost;
				if (dist[there] > nextDist) {
					dist[there] = nextDist;
					pq.offer(new Pair(there, nextDist));
				}
			}
		}
		return dist;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		adj = new ArrayList<>(); adj2 = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			adj.add(new ArrayList<>()); adj2.add(new ArrayList<>());
		}
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adj.get(from).add(new Pair(to, weight));
			adj2.get(to).add(new Pair(from, weight));
		}
		int maxDist = 0;
		// 파티가 열리는 지점에서 나머지 모든 정점까지의 최단 거리
		int[] party = dijkstra(X);
		// 간선을 반대로 뒤집에서 파티에서 모든 정점까지의 최단 거리
		int[] party2 = dijkstra2(X);
		for (int i = 1; i <= N; i++)
			maxDist = Math.max(maxDist, party[i] + party2[i]);
		System.out.println(maxDist);
	}

}

class Pair implements Comparable<Pair> {
	int num; int cost;
	
	public Pair(int n, int c) { num = n; cost = c; }
	
	@Override
	public int compareTo(Pair o) { return cost - o.cost; }
	
}
