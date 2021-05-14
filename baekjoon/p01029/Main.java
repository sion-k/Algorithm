package baekjoon.p01029;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N; static int[][] adj;
	static int[][][] cache;

	static int dp(int here, int price, int field) {
		if (cache[here][price][field] != 0) return cache[here][price][field];
		int max = Integer.bitCount(field);
		for (int there = 0; there < N; there++) {
			int nextPrice = adj[here][there];
			if ((field & (1 << there)) == 0 && nextPrice >= price)
				max = Math.max(max, dp(there, nextPrice, field | (1 << there)));
		}
		return cache[here][price][field] = max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); adj = new int[N][N];
		for (int i = 0; i < N; i++) {
			String row = br.readLine();
			for (int j = 0; j < N; j++)
				adj[i][j] = row.charAt(j) - '0';
		}
		cache = new int[N][10][(int)(Math.pow(2, N))];
		System.out.println(dp(0, 0, 1));
	}

}