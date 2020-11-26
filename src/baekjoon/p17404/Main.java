package baekjoon.p17404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] cost;
	static int[][][] cache;
	static final int INF = 1000000;

	static int dp(int p, int i, int n) {
		if (i == N - 2) {
			int min = INF;
			for (int j = 0; j < 3; j++) {
				if (j == p || j == n) {continue;}
				min = Math.min(min, cost[i][j]);
			}
			return min;
		}
		if (cache[p][i][n] != 0) {return cache[p][i][n];}
		int min = INF;
		for (int j = 0; j < 3; j++) {
			if (j == p) {continue;}
			min = Math.min(min, cost[i][j] + dp(j, i + 1, n));
		}
		return cache[p][i][n] = min;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cost = new int[N][3];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 3; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		cache = new int[3][N][3];
		int min = INF;
		for (int head = 0; head < 3; head++) {
			for (int tail = 0; tail < 3; tail++) {
				if (head == tail) {continue;}
				min = Math.min(min, cost[0][head] + dp(head, 1, tail) + cost[N - 1][tail]);
			}
		}
		System.out.println(min);
	}

}