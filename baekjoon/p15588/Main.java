package baekjoon.p15588;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static final int MOD = 1000000007;
	
	// a^b를 구한다
	static long pow(int a, int b) {
		if (b == 0) return 1;
		if (b % 2 == 0) {
			long t = pow(a, b / 2);
			return (t * t) % MOD;
		} else return (a * pow(a, b - 1)) % MOD;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		// [0, n]까지 연속되는 색깔이 K - 1개를 넘지 않게 칠하는 경우의 수
		// 즉 [1, K - 1]개만 연속될 수 있게 칠하는 경우의 수
		long[] dp = new long[N];
		for (int i = 0; i < K - 1; i++)
			dp[i] = M;
		long[] pSum = new long[N]; // dp[0, i]까지의 prefix Sum저장
		pSum[0] = dp[0];
		for (int i = 1; i < N; i++) {
			dp[i] = (dp[i] + (M - 1) * (pSum[i - 1] - (i - K >= 0 ? pSum[i - K] : 0))) % MOD;
			pSum[i] = (pSum[i - 1] + dp[i]) % MOD;
		}
		System.out.println((pow(M, N) - dp[N - 1] + MOD) % MOD);
	}
	
}
