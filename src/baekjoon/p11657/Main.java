package baekjoon.p11657;

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
	static ArrayList<ArrayList<Pair>> adjList;
	static long[][] adjArray;
	static final int INF = 987654321;
	static final int INFM = 987654321 - 10000000;

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

	static long[] BellmanFord(int src) {
		long[] upper = new long[N + 1];
		Arrays.fill(upper, 1987654321987654321L);
		upper[src] = 0;
		boolean updated = false;
		// N - 1�� ��ȭ �õ�
		for (int n = 1; n <= N - 1; n++) {
			updated = false;
			for (int here = 1; here <= N; here++) {
				for (int i = 0; i < adjList.get(here).size(); i++) {
					int there = adjList.get(here).get(i).num;
					int cost = adjList.get(here).get(i).cost;
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
			for (int i = 0; i < adjList.get(here).size(); i++) {
				int there = adjList.get(here).get(i).num;
				int cost = adjList.get(here).get(i).cost;
				// (here, there) ������ ���� ��ȭ �õ��ߴµ� ��ȭ�ǰ�
				// ������������ �� �������� ��ΰ� �ִ� ���
				if (upper[there] > upper[here] + cost && adjArray[src][there] < INFM) {
					return new long[0];
				}
			}
		}
		return upper;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		adjList = new ArrayList<>(N + 1);
		adjList.add(new ArrayList<>());
		for (int i = 1; i <= N; i++) {adjList.add(new ArrayList<>());}

		adjArray = new long[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {Arrays.fill(adjArray[i], INF);}

		int M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjArray[from][to] = Math.abs(weight);
			adjList.get(from).add(new Pair(to, weight));
		}
		Floyd();
		long[] dist = BellmanFord(1);
		if (dist.length == 0) {
			bw.write("-1"); bw.newLine();
		} else {
			for (int i = 2; i <= N; i++) {
				if (dist[i] >= INFM) {bw.write("-1");}
				else {bw.write(String.valueOf(dist[i]));}
				bw.newLine();
			}
		}
		br.close();
		bw.close();
	}

}