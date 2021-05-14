package baekjoon.p01495;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[] P;
	static int[][] cache;
	static final int INF = 1001;

	static int dp(int i, int v) {
		if (i >= N) {return v;}
		if (cache[i][v] != INF) {return cache[i][v];}
		int max = -1;
		int inc = v + P[i];
		if (inc <= M) {max = Math.max(max, dp(i + 1, inc));}
		int dec = v - P[i];
		if (dec >= 0) {max = Math.max(max, dp(i + 1, dec));}
		return cache[i][v] = max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cache = new int[N + 1][M + 1];
		for (int i = 0; i < N + 1; i++) {Arrays.fill(cache[i], INF);}
		P = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {P[i] = Integer.parseInt(st.nextToken());}
		System.out.println(dp(0, S));
	}

}