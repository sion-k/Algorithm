package baekjoon.p02188;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N; static int M;
	static int[][] capacity;
	static int[][] flow;
	
	static int networkFlow(int source, int sink) {
		int totalFlow = 0;
		// �ʺ� �켱 Ž������ ���� ��θ� ã�´�
		while (true) {
			Queue<Integer> q = new LinkedList<>();
			q.offer(source);
			int[] parent = new int[N + M + 2];
			Arrays.fill(parent, -1);
			parent[source] = source;
			// sink�� ������ �� ���� �ʺ� �켱 Ž��
			while (!q.isEmpty() && parent[sink] == -1) {
				int here = q.poll();
				// �ܿ� �뷮�� ���� ������ ���� Ž��
				for (int there = 0; there < N + M + 2; there++)
					if (capacity[here][there] - flow[here][there] > 0 && parent[there] == -1) {
						q.offer(there);
						parent[there] = here;
					}
			}
			// ���� ��ΰ� ������ ����
			if (parent[sink] == -1) break;
			// ��� �뷮�� 1�̹Ƿ� ������ο� 1��ŭ ���������
			for (int p = sink; p != source; p = parent[p]) {
				flow[parent[p]][p]++;
				flow[p][parent[p]]--;
			}
			totalFlow++;
		}
		return totalFlow;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 0��° ������ source, N + M + 1��° ������ sink
		capacity = new int[N + M + 2][N + M + 2]; flow = new int[N + M + 2][N + M + 2];
		// [1, N]������ ��, [N + 1, N + M]������ ��縦 ��Ÿ����
		for (int i = 1; i <= N; i++) {
			capacity[0][i] = 1; // source�� �� ������ ����
			st = new StringTokenizer(br.readLine(), " ");
			int K = Integer.parseInt(st.nextToken());
			for (int j = 0; j < K; j++) capacity[i][N + Integer.parseInt(st.nextToken())] = 1;
		}
		// ���� sink ������ ����
		for (int i = 1; i <= M; i++) capacity[N + i][N + M + 1] = 1;
		System.out.println(networkFlow(0, N + M + 1));
	}
	
}