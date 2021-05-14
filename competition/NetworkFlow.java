package competition;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class NetworkFlow {
	// ����� �������� ����
	static int[][] capacity = new int[52][52];
	static int[][] flow = new int[52][52];

	static final int V = 52;
	static final int INF = 987654321;

	static int networkFlow(int source, int sink) {
		int totalFlow = 0;
		while (true) {
			// BFS�� ���� ��θ� ã�´�
			int[] parent = new int[V];
			Arrays.fill(parent, -1);
			parent[source] = source;
			Queue<Integer> q = new LinkedList<>();
			q.offer(source);
			while (!q.isEmpty() && parent[sink] == -1) {
				int here = q.poll();
				// �ܿ� �뷮�� ���� ������ ���� Ž��
				for (int there = 0; there < V; there++) {
					if (capacity[here][there] - flow[here][there] > 0 && parent[there] == -1) {
						q.offer(there);
						parent[there] = here;
					}
				}
			}
			// ���� ��ΰ� ������ ����
			if (parent[sink] == -1) break;
			// ���� ��θ� ���� ������ �󸶳� ������ ����
			int amount = INF;
			for (int p = sink; p != source; p = parent[p])
				amount = Math.min(amount, capacity[parent[p]][p] - flow[parent[p]][p]);
			// ���� ��θ� ���� ������ ������
			for (int p = sink; p != source; p = parent[p]) {
				flow[parent[p]][p] += amount;
				flow[p][parent[p]] -= amount;
			}
			totalFlow += amount;
		}
		return totalFlow;
	}
	
}
