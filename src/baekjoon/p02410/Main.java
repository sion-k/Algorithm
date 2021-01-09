package baekjoon.p02410;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static final int MOD = 1_000_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] dp = new int[20][N + 1];
		// ±âÀú »ç·Ê n = 0
		for (int p = 0; p < 20; p++)
			dp[p][0] = 1;
		for (int n = 1; n <= N; n++) {
			for (int p = 0; p < 20; p++) {
				int sum = 0;
				for (int next = p; (1 << next) <= n; next++)
					sum = (sum + dp[next][n - (1 << next)]) % MOD;
				dp[p][n] = sum;
			}
		}
		System.out.println(dp[0][N]);
	}

}