package baekjoon.p02098;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N; static int[][] adj;
	static int[][][] cache;
	static final int INF = 16000000;

	static int dp(int start, int here, int visit) {
		visit |= (1 << here);
		if (Integer.bitCount(visit) == N) {
			return adj[here][start] == 0 ? INF : adj[here][start];
		}
		if (cache[start][here][visit] != 0) {return cache[start][here][visit];}
		int min = INF;
		for (int there = 0; there < N; there++) {
			if (adj[here][there] != 0 && (visit & (1 << there)) == 0) {
				min = Math.min(min, adj[here][there] + dp(start, there, visit));
			}
		}
		return cache[start][here][visit] = min;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); adj = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				adj[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		cache = new int[N][N][(int)(Math.pow(2, N))];
		int min = INF;
		for (int i = 0; i < N; i++) {
			min = Math.min(min, dp(i, i, 0));
		}
		System.out.println(min);
	}

}