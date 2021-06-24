package baekjoon.p02157;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] adj;
	static int[][] cache;
	static final int NINF = -10000000;

	// here번째 도시에서 앞으로 최대 k개의 도시를 지날 수 있을 때
	// N번째 도시까지 갈 때 얻는 최대 기내식 점수
	static int dp(int here, int k) {
		if (here == N) return 0;
		if (cache[here][k] != -1) return cache[here][k];
		if (k == 0) return cache[here][k] = NINF;
		int max = NINF;
		for (int there = here + 1; there <= N; there++)
			if (adj[here][there] != 0)
				max = Math.max(max, adj[here][there] + dp(there, k - 1));
		return cache[here][k] = max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		adj = new int[N + 1][N + 1];
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (a < b)
				adj[a][b] = Math.max(adj[a][b], c);
		}
		cache = new int[N + 1][M + 1];
		for (int i = 0; i < N + 1; i++)
			Arrays.fill(cache[i], -1);
		System.out.println(dp(1, M - 1));
	}

}