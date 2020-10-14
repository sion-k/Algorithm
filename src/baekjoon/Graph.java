package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Graph {
	static int N; // 정점 수
	static int M; // 간선 수
	static boolean[][] EDGE;// 인접 행렬 표현법

	// 인접 리스트 표현법
	static class Pair implements Comparable<Pair> {
		int num; int cost;
		public Pair(int n, int c) {num = n; cost = c;}
		@Override
		public int compareTo(Pair o) {
			return cost - o.cost;
		}
	}

	static boolean[] VISIT;// DFS용
	static boolean[] BOOKED;// BFS용
	static int[] DIS;// 최단 거리

	// 상하좌우 이동
	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};

	//[0, N)
	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}

	// 정점 here에서 dfs
	static void dfs(int here) {
		VISIT[here] = true;
		for (int next = 1; next <= N; next++) {
			if(EDGE[here][next] && !VISIT[next]) {
				dfs(next);
			}
		}
	}

	// 정점 start에서 bfs
	static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		BOOKED[start] = true;

		while(!q.isEmpty()) {
			int here = q.poll();
			for (int next = 1; next <= N; next++) {
				if(EDGE[here][next] && !BOOKED[next]) {
					q.offer(next);
					BOOKED[next] = true;
				}
			}
		}
	}

	// 정점 from에서 to까지 최단 거리 반환. 도달 불가능하면 -1 반환
	static int bfs(int from, int to) {
		Queue<Integer> q = new LinkedList<>();
		q.add(from); BOOKED[from] = true; DIS[from] = 0;

		while(!q.isEmpty()) {
			int here = q.poll();
			for (int next = 1; next <= N; next++) {
				if(EDGE[here][next] && !BOOKED[next]) {
					if (next == to) {return DIS[here] + 1;}
					q.offer(next); BOOKED[next] = true;
					DIS[next] = DIS[here] + 1;
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		VISIT = new boolean[N + 1];
		BOOKED = new boolean[N + 1];
		EDGE = new boolean[N + 1][N + 1];

		M = Integer.parseInt(st.nextToken());

		int V = Integer.parseInt(st.nextToken());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int here = Integer.parseInt(st.nextToken());
			int next = Integer.parseInt(st.nextToken());
			EDGE[here][next] = EDGE[next][here]  = true;
		}
		dfs(V);
		bfs(V);
	}

}
class Dijkstra {
	static class Pair implements Comparable<Pair> {
		int num; int cost;
		public Pair(int n, int c) {num = n; cost = c;}
		@Override
		public int compareTo(Pair o) {
			return cost - o.cost;
		}
	}
	static int V; // 정점의 개수, 정점의 번호는 [1, V]이다
	static ArrayList<ArrayList<Pair>> adj;

	// 시작점 src로 부터 최단 경로 길이를 담은 배열을 반환한다
	static int[] dijkstra(int src) {
		int[] dist = new int[V + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[src] = 0;// src 자신으로의 최단 경로 길이는 0
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.offer(new Pair(src, 0));
		while(!pq.isEmpty()) {
			Pair p = pq.poll(); int here = p.num; int cost = p.cost;
			if(dist[here] < cost) {continue;}
			for (int i = 0; i < adj.get(here).size(); i++) {
				int there = adj.get(here).get(i).num;
				int nextDist = cost + adj.get(here).get(i).cost;
				// 기존에 발견한 것보다 더 짧은 경로를 발견 한 경우
				if (dist[there] > nextDist) {
					dist[there] = nextDist;
					pq.offer(new Pair(there, nextDist));
				}
			}
		}
		return dist;
	}
}