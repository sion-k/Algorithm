package baekjoon.p20007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static final int INF = 987654321;
	static ArrayList<ArrayList<Pair>> adj;

	static int[] dijkstra(int src) {
		int[] dist = new int[N];
		Arrays.fill(dist, INF);
		dist[src] = 0;
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.offer(new Pair(src, 0));
		while (!pq.isEmpty()) {
			Pair p = pq.poll();
			int here = p.num; int cost = p.cost;
			// 더 나은 경로를 알고 있다면 무시
			if(dist[here] < cost) {continue;}
			// 정점을 방문하고 인접 정점을 검사
			for (Pair edge : adj.get(here)) {
				int there = edge.num;
				int nextDist = cost + edge.cost;
				// 기존에 발견한 것보다 더 짧은 경로를 발견 한 경우 최신화
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
		adj = new ArrayList<>(N);
		for (int i = 0; i < N; i++) {adj.add(new ArrayList<>());}
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adj.get(start).add(new Pair(end, weight));
			adj.get(end).add(new Pair(start, weight));
		}

		int[] dist = dijkstra(Y);
		Arrays.sort(dist);
		int days = 1; int cost = 0;
		for (int thereDist : dist) {
			int roundTrip = 2 * thereDist;
			if (roundTrip > X) {days = -1; break;}
			else if ((cost += roundTrip) > X){
				cost = roundTrip; days++;
			}
		}
		System.out.println(days);
	}

}

class Pair implements Comparable<Pair> {
	int num; int cost;
	public Pair(int n, int c) {num = n; cost = c;}
	@Override
	public int compareTo(Pair o) {return cost - o.cost;}
}