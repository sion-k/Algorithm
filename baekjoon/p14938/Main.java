package baekjoon.p14938;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] item;
	static int[][] adj;
	static final int INF = 1501;

	static void Floyd() {
		for (int i = 1; i <= N; i++) {adj[i][i] = 0;}
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		adj = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {Arrays.fill(adj[i], INF);}
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		item = new int[N + 1];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {item[i] = Integer.parseInt(st.nextToken());}
		for (int i = 1; i <= R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adj[from][to] = adj[to][from] = Math.min(adj[from][to], weight);
		}
		Floyd();
		int max = 0;
		for (int i = 1; i <= N; i++) {
			int sum = 0;
			for (int j = 1; j <= N; j++) {if (adj[i][j] <= M) {sum += item[j];}}
			max = Math.max(max, sum);
		}
		System.out.println(max);
	}

}