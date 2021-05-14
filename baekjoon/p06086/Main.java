package baekjoon.p06086;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
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

	static int toNumber(char ch) {
		if (Character.isUpperCase(ch)) return ch - 'A';
		else return ch - 'a' + 26;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int u = toNumber(st.nextToken().charAt(0));
			int v = toNumber(st.nextToken().charAt(0));
			int w = Integer.parseInt(st.nextToken());
			capacity[u][v] += w;
			capacity[v][u] += w;
		}
		System.out.println(networkFlow(0, 25));
	}

}