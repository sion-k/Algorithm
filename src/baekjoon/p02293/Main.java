package baekjoon.p02293;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] COIN;
	static int[][] cache;

	static int dp(int K, int prev) {
		if (K == 0) {return 1;}
		if (cache[K][prev] != -1) {return cache[K][prev];}
		cache[K][prev] = 0; int i = prev;
		while (i < N && K - COIN[i] >= 0) {
			cache[K][prev] += dp(K - COIN[i], i); i++;
		}
		return cache[K][prev];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		cache = new int[K + 1][N];
		for (int i = 1; i <= K; i++) {Arrays.fill(cache[i], -1);}
		COIN = new int[N];
		for (int i = 0; i < N; i++) {COIN[i] = Integer.parseInt(br.readLine());}
		Arrays.sort(COIN);
		System.out.println(dp(K, 0));
	}
}