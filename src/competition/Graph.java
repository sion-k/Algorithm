package competition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Graph {
	static int V; // 정점의 개수
	static boolean[][] adj;// 인접 행렬 표현법
	static boolean[] visit;

	static int N; static int M; // 2차원
	static final int[] dy = { -1, 1, 0, 0 };
	static final int[] dx = { 0, 0, -1, 1 };
	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	// 정점 here에서 dfs
	static void dfs(int here) {
		visit[here] = true;
		for (int next = 0; next < V; next++)
			if (adj[here][next] && !visit[next])
				dfs(next);
	}

	// 정점 start에서 end까지 최단 거리 반환. 도달 불가능하면 -1 반환
	static int bfs(int start, int end) {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		int[] dist = new int[V];
		Arrays.fill(dist, -1);
		dist[start] = 0;
		while (!q.isEmpty()) {
			int here = q.poll();
			for (int next = 0; next < V; next++) {
				if (adj[here][next] && dist[next] != -1) {
					if (next == end) {return dist[here] + 1;}
					q.offer(next);
					dist[next] = dist[here] + 1;
				}
			}
		}
		return -1;
	}

	static final int INF = 0; // 존재할 수 있는 최대 경로의 길이 + 1
	static int[][] adjArray; // 인접 행렬 표현법
	static ArrayList<ArrayList<Pair>> adjList; // 인접 리스트 표현법
	static boolean[][] reachable;

	// 정점 src에서 다른 모든 정점까지의 최단거리를 담은 dist[]를 반환
	static int[] dijkstra(int src) {
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.offer(new Pair(src, 0));
		int[] dist = new int[V + 1];
		Arrays.fill(dist, INF);
		dist[src] = 0;
		while (!pq.isEmpty()) {
			Pair p = pq.poll();
			int here = p.num; int cost = p.cost;
			// 더 나은 경로를 알고 있다면 무시
			if(dist[here] < cost) {continue;}
			// 정점을 방문하고 인접 정점을 검사
			for (Pair edge : adjList.get(here)) {
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

	// 정점 src에서 다른 모든 정점까지의 최단거리를 담은 upper[]를 반환
	// 만약 음수 사이클이 존재하는 경우 빈 배열을 반환한다
	static int[] bellmanFord(int src) {
		int[] upper = new int[V + 1];
		Arrays.fill(upper, INF);
		upper[src] = 0;
		boolean updated = false;
		// N - 1번 완화 시도
		for (int n = 1; n <= N - 1; n++) {
			updated = false;
			for (int here = 1; here <= N; here++) {
				for (Pair edge : adjList.get(here)) {
					int there = edge.num; int cost = edge.cost;
					// (here, there) 간선을 따라 완화 시도
					if (upper[there] > upper[here] + cost) {
						upper[there] = upper[here] + cost;
						updated = true;
					}
				}
			}
			// 모든 간선에 대해 완화시도했는데 완화되지 않으면 최단 경로
			if (!updated) {break;}
		}
		// N번째의 완화 시도
		for (int here = 1; here <= N; here++) {
			for (Pair edge : adjList.get(here)) {
				int there = edge.num; int cost = edge.cost;
				// (here, there) 간선을 따라 완화 시도했는데 완화되고
				// 시작지점에서 그 지점까지 경로가 있는 경우
				if (upper[there] > upper[here] + cost && adjArray[src][there] < INF) {
					return new int[0];
				}
			}
		}
		return upper;
	}

	// 모든 정점간의 최단 거리를 adjArray에 저장
	// 선 조건 : adj(i, j) (간선이 존재하지 않으면 INF로 초기화)
	static void Floyd() {
		// 자기 자신으로의 최단 경로는 0
		for (int i = 1; i <= N; i++) {adjArray[i][i] = 0;}
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					adjArray[i][j] = Math.min(adjArray[i][j], adjArray[i][k] + adjArray[k][j]);
				}
			}
		}
	}

	// 모든 정점간의 도달 가능성 여부를 reachable에 저장
	// 선 조건 : adj(i, j) 간선이 존재하면 true
	static void FolydReachable() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					reachable[i][j] = reachable[i][j] || (reachable[i][k] && reachable[k][j]);
				}
			}
		}
	}
}

class Pair implements Comparable<Pair> {
	int num; int cost;
	public Pair(int n, int c) {num = n; cost = c;}
	@Override
	public int compareTo(Pair o) {return cost - o.cost;}
}