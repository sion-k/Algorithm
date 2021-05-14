package baekjoon.p01238;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N; // 정점 번호 : [1, N]
	static int X; // 파티
	static ArrayList<ArrayList<Pair>> adj;
	static final int INF = 100001;

	static int[] Dijkstra(int src) {
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

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		adj = new ArrayList<>(); adj.add(new ArrayList<>());
		for (int i = 1; i <= N; i++) {adj.add(new ArrayList<>());}
		int M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adj.get(from).add(new Pair(to, weight));
		}
		int maxDist = 0;
		int[] party = Dijkstra(X);
		for (int i = 1; i <= N; i++) {
			int dist = Dijkstra(i)[X] + party[i];
			maxDist = Math.max(maxDist, dist);
		}
		System.out.println(maxDist);
	}

}

class Pair implements Comparable<Pair> {
	int num; int cost;
	public Pair(int n, int c) {num = n; cost = c;}
	@Override
	public int compareTo(Pair o) {return cost - o.cost;}
}