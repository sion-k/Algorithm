package baekjoon.p05719;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static ArrayList<ArrayList<Pair>> adj;
	static ArrayList<ArrayList<Pair>> adj2;
	static boolean[][] removed;
	
	static final int INF = 987654321;
	
	static int[] dijkstra(int start) {
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.offer(new Pair(start, 0));
		int[] dist = new int[N];
		Arrays.fill(dist, INF);
		dist[start] = 0;
		while (!pq.isEmpty()) {
			Pair p = pq.poll();
			int here = p.num; int cost = p.cost;
			if (dist[here] < cost) continue;
			for (Pair edge : adj.get(here)) if (!removed[here][edge.num]) {
				int there = edge.num;
				int nextCost = cost + edge.cost;
				if (dist[there] > nextCost) {
					dist[there] = nextCost;
					pq.offer(new Pair(there, nextCost));
				}
			}
		}
		return dist;
	}
	
	// here에서 역방향 간선을 찾아가면서 최단경로에 속하는 간선을 지운다
	static void bfs(int[] dist, int start) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		boolean[] booked = new boolean[N];
		booked[start] = true;
		while (!q.isEmpty()) {
			int here = q.poll();
			for (Pair edge : adj2.get(here)) {
				int there = edge.num; int weight = edge.cost;
				if (dist[there] == dist[here] - weight) {
					if (!booked[there]) q.offer(there);
					booked[there] = true;
					removed[there][here] = true;
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			if (N == 0 && M == 0) break;
			st = new StringTokenizer(br.readLine(), " ");
			int S = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());
			adj = new ArrayList<>(N); adj2 = new ArrayList<>(N);
			for (int i = 0; i < N; i++) {
				adj.add(new ArrayList<>());
				adj2.add(new ArrayList<>());
			}
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				adj.get(u).add(new Pair(v, p));
				adj2.get(v).add(new Pair(u, p));
			}
			removed = new boolean[N][N];
			bfs(dijkstra(S), D);
			int ret = dijkstra(S)[D];
			bw.append(ret == INF ? -1 : ret).append("\n");
		}
		System.out.print(bw);
	}
	
}

class Pair implements Comparable<Pair> {
	int num, cost;

	Pair(int t, int w) { num = t; cost = w; }
	
	@Override
	public int compareTo(Pair o) { return cost - o.cost; }
	
}
