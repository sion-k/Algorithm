package baekjoon.p02410;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static final int MOD = 1_000_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N + 1];
		dp[0] = 1;
		for (int i = 1; i <= N; i++)
			dp[i] = (i % 2 == 0) ? (dp[i - 1] + dp[i / 2]) % MOD : dp[i - 1];
		System.out.println(dp[N]);
	}

}