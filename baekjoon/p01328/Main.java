package baekjoon.p01328;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][][] cache;

	static final int MOD = 1000000007;

	static int dp(int N, int L, int R) {
		if (L == 0 || R == 0) {return 0;}
		if (L + R > N + 1) {return 0;}
		if (N == 1) {return (L == 1 && R == 1) ? 1 : 0;}
		if (cache[N][L][R] != -1) {return cache[N][L][R];}
		int sum = (int)(((long)(N - 2) * dp(N - 1, L, R)) % MOD);
		sum = (sum + dp(N - 1, L - 1, R)) % MOD;
		sum = (sum + dp(N - 1, L, R - 1)) % MOD;
		return cache[N][L][R] = sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		cache = new int[N + 1][L + 1][R + 1];
		for (int i = 0; i < N + 1; i++)
			for (int j = 0; j < L + 1; j++)
				Arrays.fill(cache[i][j], -1);
		System.out.println(dp(N, L, R));
	}

}