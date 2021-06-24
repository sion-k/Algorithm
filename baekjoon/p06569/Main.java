package baekjoon.p06569;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static long[][] cache;
	
	static long dp(int i, int b) {
		if (i == N) return b == 0 ? 1 : 0;
		if (cache[i][b] != -1) return cache[i][b];
		return cache[i][b] = btk(i, 0, b, 0);
	}
	
	static long btk(int i, int j, int b, int nb) {
		if (j >= M) return dp(i + 1, nb);
		if ((b & (1 << j)) > 0) return btk(i, j + 1, b, nb);
		long sum = btk(i, j + 1, b, nb | (1 << j)); // 세로로 놓는 경우
		if (j + 1 < M && ((b & (1 << (j + 1))) == 0)) // 가로로 놓는 경우
			sum += btk(i, j + 2, b, nb);
		return sum;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		if (N < M) {int temp = N; N = M; M = temp;}
		cache = new long[N][1 << M];
		for (int i = 0; i < N; i++) Arrays.fill(cache[i], -1);
		while (N != 0) {
			ans.append(dp(0, 0)).append("\n");
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			if (N < M) {int temp = N; N = M; M = temp;}
			cache = new long[N][1 << M];
			for (int i = 0; i < N; i++) Arrays.fill(cache[i], -1);
		}
		System.out.print(ans);
	}
	
}