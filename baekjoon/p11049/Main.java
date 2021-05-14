package baekjoon.p11049;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] cache;
	static int[][] M;

	static int dp(int i, int j) {
		if (i == j) {return 0;}
		if (cache[i][j] != 0) {return cache[i][j];}
		int min = Integer.MAX_VALUE;
		for (int k = i; k <= j - 1; k++) {
			min = Math.min(min, dp(i, k) + dp(k + 1, j) +
			M[i][0] * M[k][1] * M[j][1]);
		}
		return cache[i][j] = min;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		M = new int[N][2]; cache = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			M[i][0] = Integer.parseInt(st.nextToken());
			M[i][1] = Integer.parseInt(st.nextToken());
		}
		System.out.println(dp(0, N - 1));
	}

}