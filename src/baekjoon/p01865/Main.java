package baekjoon.p01865;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Pair {
	int num; int cost;
	public Pair(int n, int c) {num = n; cost = c;}
}

public class Main {
	static int N;
	static ArrayList<ArrayList<Pair>> adj; // ���� ����Ʈ ǥ����
	static boolean[][] reachable;

	static int[] BellmanFord(int src) {
		int[] upper = new int[N + 1];
		Arrays.fill(upper, Integer.MAX_VALUE);
		upper[src] = 0;
		boolean updated = false;
		// N - 1�� ��ȭ �õ�
		for (int i = 1; i <= N - 1; i++) {
			updated = false;
			for (int here = 1; here <= N; here++) {
				for (Pair edge : adj.get(here)) {
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
			for (Pair edge : adj.get(here)) {
				int there = edge.num; int cost = edge.cost;
				// (here, there) ������ ���� ��ȭ �õ��ߴµ� ��ȭ�ǰ�
				// ������������ �� �������� ��ΰ� �ְ�, ���ƿ� ���� �ִ� ���
				if (upper[there] > upper[here] + cost &&
					reachable[src][there] && reachable[there][src]) {
					return new int[0];
				}
			}
		}
		return upper;
	}

	// �� ���� : adj(i, j) ������ �����ϸ� true
	static void Folyd() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					reachable[i][j] = reachable[i][j] || (reachable[i][k] && reachable[k][j]);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			adj = new ArrayList<>(N + 1);
			adj.add(new ArrayList<>());
			for (int i = 0; i < N; i++) {adj.add(new ArrayList<>());}
			reachable = new boolean[N + 1][N + 1];

			int M = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				adj.get(start).add(new Pair(end, weight));
				adj.get(end).add(new Pair(start, weight));
				reachable[start][end] = reachable[end][start] = true;
			}

			for (int i = 0; i < W; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				adj.get(start).add(new Pair(end, -weight));
				reachable[start][end] = true;
			}
			Folyd();
			boolean ret = false;
			for (int i = 1; i <= N; i++) {
				int[] dist = BellmanFord(i);
				if (dist.length == 0) {ret = true;}
			}
			if (ret) {bw.write("YES");}
			else {bw.write("NO");}
			bw.newLine();
		}
		bw.close();
	}

}