package baekjoon.p10217;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] C;
	static int[][] D;
	static long[][] cache;

	static final long INF = 987654321098765432L;

	static long dp(int here, int leftCost) {
		if (here == N) return 0;
		if (cache[here][leftCost] != 0) return cache[here][leftCost];
		long min = INF;
		for (int there = 1; there <= N; there++)
			if (C[here][there] != 0 && C[here][there] <= leftCost)
				min = Math.min(min, D[here][there] + dp(there, leftCost - C[here][there]));

		return cache[here][leftCost] = min;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("testCase.txt"));
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			C = new int[N + 1][N + 1]; D = new int[N + 1][N + 1];
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				C[u][v] = c; D[u][v] = d;
			}
			cache = new long[N + 1][M + 1];
			long ret = dp(1, M);
			if (ret >= INF)
				bw.write("Poor KCM");
			else
				bw.write(String.valueOf(ret));
			bw.newLine();
		}
		bw.close();
	}

}