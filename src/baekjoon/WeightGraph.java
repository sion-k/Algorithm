package baekjoon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class WeightGraph {
	static int N; // [1, N] 정점의 개수
	static final int INF = 0; // 존재할 수 있는 최대 경로의 길이 + 1
	static int[][] adjArray; // 인접 행렬 표현법
	static ArrayList<ArrayList<Pair>> adjList; // 인접 리스트 표현법
	static boolean[][] reachable;

	static int[] dijkstra(int src) {
		int[] dist = new int[N + 1];
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
			for (int i = 0; i < adjList.get(here).size(); i++) {
				int there = adjList.get(here).get(i).num;
				int nextDist = cost + adjList.get(here).get(i).cost;
				// 기존에 발견한 것보다 더 짧은 경로를 발견 한 경우 최신화
				if (dist[there] > nextDist) {
					dist[there] = nextDist;
					pq.offer(new Pair(there, nextDist));
				}
			}
		}
		return dist;
	}

	static int[] bellmanFord(int src) {
		int[] upper = new int[N + 1];
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