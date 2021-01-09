package baekjoon.p02410;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[][] cache;
	static final int MOD = 1_000_000_000;

	// 오름차순으로만 2의 멱수의 집합을 나타낸다
	// 이전에 2^p를 사용했을 때 n을 표현하는 경우의 수
	static int dp(int p, int n) {
		if (n == 0) return 1;
		if (cache[p][n] != 0) return cache[p][n];
		int sum = 0;
		for (int next = p; (1 << next) <= n; next++)
			sum = (sum + dp(next, n - (1 << next))) % MOD;
		return cache[p][n] = sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		cache = new int[20][N + 1];
		System.out.println(dp(0, N));
		int[][] dp = new int[20][N + 1];

	}

}