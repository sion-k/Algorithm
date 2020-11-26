package baekjoon.p01562;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int[][][] cache;
	static final int MOD = 1000000000;

	static int dp(int p, int n, int k) {
		if (n == 0) {return k == 1023 ? 1 : 0;}
		if (cache[p][n][k] != -1) {return cache[p][n][k];}
		int sum = 0;
		if (p == 0) {
			sum = (sum + dp(1, n - 1, k | (1 << 1))) % MOD;
		} else if (p == 9) {
			sum = (sum + dp(8, n - 1, k | (1 << 8))) % MOD;
		} else {
			int k1 = k | (1 << (p - 1)); int k2 = k | (1 << (p + 1));
			sum = (sum + dp(p - 1, n - 1, k1)) % MOD;
			sum = (sum + dp(p + 1, n - 1, k2)) % MOD;
		}
		return cache[p][n][k] = sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = 0;
		cache = new int[10][N + 1][1024];
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < N + 1; j++)
				Arrays.fill(cache[i][j], -1);
		int sum = 0;
		for (int i = 1; i <= 9; i++) {
			sum = (sum + dp(i, N - 1, (K | (1 << i)))) % MOD;
		}
		System.out.println(sum);
	}

}