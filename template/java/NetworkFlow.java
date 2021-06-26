package competition;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class NetworkFlow {
	// 양방향 간선인지 유의
	static int[][] capacity = new int[52][52];
	static int[][] flow = new int[52][52];

	static final int V = 52;
	static final int INF = 987654321;

	static int networkFlow(int source, int sink) {
		int totalFlow = 0;
		while (true) {
			// BFS로 증가 경로를 찾는다
			int[] parent = new int[V];
			Arrays.fill(parent, -1);
			parent[source] = source;
			Queue<Integer> q = new LinkedList<>();
			q.offer(source);
			while (!q.isEmpty() && parent[sink] == -1) {
				int here = q.poll();
				// 잔여 용량이 남은 간선을 따라 탐색
				for (int there = 0; there < V; there++) {
					if (capacity[here][there] - flow[here][there] > 0 && parent[there] == -1) {
						q.offer(there);
						parent[there] = here;
					}
				}
			}
			// 증가 경로가 없으면 종료
			if (parent[sink] == -1) break;
			// 증가 경로를 통해 유량을 얼마나 보낼지 결정
			int amount = INF;
			for (int p = sink; p != source; p = parent[p])
				amount = Math.min(amount, capacity[parent[p]][p] - flow[parent[p]][p]);
			// 증가 경로를 통해 유량을 보낸다
			for (int p = sink; p != source; p = parent[p]) {
				flow[parent[p]][p] += amount;
				flow[p][parent[p]] -= amount;
			}
			totalFlow += amount;
		}
		return totalFlow;
	}
	
}
