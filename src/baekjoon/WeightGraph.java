package baekjoon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class WeightGraph {
	static int N; // [1, N] ������ ����
	static final int INF = 0; // ������ �� �ִ� �ִ� ����� ���� + 1
	static int[][] adjArray; // ���� ��� ǥ����
	static ArrayList<ArrayList<Pair>> adjList; // ���� ����Ʈ ǥ����
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
			// �� ���� ��θ� �˰� �ִٸ� ����
			if(dist[here] < cost) {continue;}
			// ������ �湮�ϰ� ���� ������ �˻�
			for (int i = 0; i < adjList.get(here).size(); i++) {
				int there = adjList.get(here).get(i).num;
				int nextDist = cost + adjList.get(here).get(i).cost;
				// ������ �߰��� �ͺ��� �� ª�� ��θ� �߰� �� ��� �ֽ�ȭ
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