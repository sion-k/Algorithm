package baekjoon.p02666;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N; static int M; static int[] S;
	static int[][][] cache;

	static int dp(int i, int j, int k) {
		if (k == M) {return 0;}
		if (cache[i][j][k] != -1) {return cache[i][j][k];}
		if (i + 1 == S[k] || j + 1 == S[k])
			return cache[i][j][k] = dp(i, j, k + 1);
		int min = N;
		if (i - 1 > 0) min = Math.min(min, 1 + dp(i - 1, j, k));
		if (i + 1 < j ) {
			min = Math.min(min, 1 + dp(i + 1, j, k));
			min = Math.min(min, 1 + dp(i, j - 1, k));
		}
		if (j + 1 < N) min = Math.min(min, 1 + dp(i, j + 1, k));
		return cache[i][j][k] = min;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(br.readLine()); S = new int[M];
		for (int i = 0; i < M; i++)
			S[i] = Integer.parseInt(br.readLine());
		cache = new int[N][N][M];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				Arrays.fill(cache[i][j], -1);
		System.out.println(dp(a, b, 0));
	}

}