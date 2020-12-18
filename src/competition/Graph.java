package competition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Graph {
	static int V; // ������ ����
	static boolean[][] adj;// ���� ��� ǥ����
	static boolean[] visit;

	static int N; static int M; // 2����
	static final int[] dy = { -1, 1, 0, 0 };
	static final int[] dx = { 0, 0, -1, 1 };
	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	// ���� here���� dfs
	static void dfs(int here) {
		visit[here] = true;
		for (int next = 0; next < V; next++)
			if (adj[here][next] && !visit[next])
				dfs(next);
	}

	// ���� start���� end���� �ִ� �Ÿ� ��ȯ. ���� �Ұ����ϸ� -1 ��ȯ
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

	static final int INF = 0; // ������ �� �ִ� �ִ� ����� ���� + 1
	static int[][] adjArray; // ���� ��� ǥ����
	static ArrayList<ArrayList<Pair>> adjList; // ���� ����Ʈ ǥ����
	static boolean[][] reachable;

	// ���� src���� �ٸ� ��� ���������� �ִܰŸ��� ���� dist[]�� ��ȯ
	static int[] dijkstra(int src) {
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.offer(new Pair(src, 0));
		int[] dist = new int[V + 1];
		Arrays.fill(dist, INF);
		dist[src] = 0;
		while (!pq.isEmpty()) {
			Pair p = pq.poll();
			int here = p.num; int cost = p.cost;
			// �� ���� ��θ� �˰� �ִٸ� ����
			if(dist[here] < cost) {continue;}
			// ������ �湮�ϰ� ���� ������ �˻�
			for (Pair edge : adjList.get(here)) {
				int there = edge.num;
				int nextDist = cost + edge.cost;
				// ������ �߰��� �ͺ��� �� ª�� ��θ� �߰� �� ��� �ֽ�ȭ
				if (dist[there] > nextDist) {
					dist[there] = nextDist;
					pq.offer(new Pair(there, nextDist));
				}
			}
		}
		return dist;
	}

	// ���� src���� �ٸ� ��� ���������� �ִܰŸ��� ���� upper[]�� ��ȯ
	// ���� ���� ����Ŭ�� �����ϴ� ��� �� �迭�� ��ȯ�Ѵ�
	static int[] bellmanFord(int src) {
		int[] upper = new int[V + 1];
		Arrays.fill(upper, INF);
		upper[src] = 0;
		boolean updated = false;
		// N - 1�� ��ȭ �õ�
		for (int n = 1; n <= N - 1; n++) {
			updated = false;
			for (int here = 1; here <= N; here++) {
				for (Pair edge : adjList.get(here)) {
					int there = edge.num; int cost = edge.cost;
					// (here, there) ������ ���� ��ȭ �õ�
					if (upper[there] > upper[here] + cost) {
						upper[there] = upper[here] + cost;
						updated = true;
					}
				}
			}
			// ��� ������ ���� ��ȭ�õ��ߴµ� ��ȭ���� ������ �ִ� ���
			if (!updated) {break;}
		}
		// N��°�� ��ȭ �õ�
		for (int here = 1; here <= N; here++) {
			for (Pair edge : adjList.get(here)) {
				int there = edge.num; int cost = edge.cost;
				// (here, there) ������ ���� ��ȭ �õ��ߴµ� ��ȭ�ǰ�
				// ������������ �� �������� ��ΰ� �ִ� ���
				if (upper[there] > upper[here] + cost && adjArray[src][there] < INF) {
					return new int[0];
				}
			}
		}
		return upper;
	}

	// ��� �������� �ִ� �Ÿ��� adjArray�� ����
	// �� ���� : adj(i, j) (������ �������� ������ INF�� �ʱ�ȭ)
	static void Floyd() {
		// �ڱ� �ڽ������� �ִ� ��δ� 0
		for (int i = 1; i <= N; i++) {adjArray[i][i] = 0;}
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					adjArray[i][j] = Math.min(adjArray[i][j], adjArray[i][k] + adjArray[k][j]);
				}
			}
		}
	}

	// ��� �������� ���� ���ɼ� ���θ� reachable�� ����
	// �� ���� : adj(i, j) ������ �����ϸ� true
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