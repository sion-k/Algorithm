import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static ArrayList<ArrayList<Pair>> adj;

	static final int INF = 50000000;

	static int[] dijkstra(int src) {
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.offer(new Pair(src, 0));
		int[] dist = new int[N + 1];
		Arrays.fill(dist, INF);
		dist[src] = 0;

		while (!pq.isEmpty()) {
			Pair p = pq.poll();
			int here = p.num; int weight = p.weight;
			if (dist[here] < weight) {continue;}
			for (Pair edge : adj.get(here)) {
				int there = edge.num; int nextWeight = weight + edge.weight;
				if (dist[there] > nextWeight) {
					dist[there] = nextWeight;
					pq.offer(new Pair(there, nextWeight));
				}
			}
		}
		return dist;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		adj = new ArrayList<>(N + 1);
		adj.add(new ArrayList<>());
		for (int i = 1; i <= N; i++) {adj.add(new ArrayList<>());}
		int P = Integer.parseInt(st.nextToken());
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adj.get(start).add(new Pair(end, weight));
			adj.get(end).add(new Pair(start, weight));
		}
		int[] startDist = dijkstra(1);
		int min = startDist[N];
		int cand = startDist[P] + dijkstra(P)[N];
		System.out.println(min == cand ? "SAVE HIM" : "GOOD BYE");
	}

}
class Pair implements Comparable<Pair> {
	int num; int weight;
	public Pair(int t, int w) {num = t; weight = w;}
	@Override
	public int compareTo(Pair o) {return weight - o.weight;}
}
