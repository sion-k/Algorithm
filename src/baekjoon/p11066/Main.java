package baekjoon.p11066;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] cache;
	static int[] S;

	// [i, j] 파일을 합칠 때 드는 최소 비용
	static int dp(int i, int j) {
		if (i == j) {return 0;}
		if (cache[i][j] != 0) {return cache[i][j];}
		int min = dp(i, i) + dp(i + 1, j) + sum(i, j);
		for (int k = i + 1; k <= j - 1; k++) {
			min = Math.min(min, dp(i , k) + dp(k + 1, j) + sum(i, j));
		}
		return cache[i][j] = min;
	}

	// [i, j]구간 합 반환
	static int sum(int i, int j) {
		return S[j] - S[i - 1];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			int K = Integer.parseInt(br.readLine());
			S = new int[K + 1];
			cache = new int[K + 1][K + 1];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= K; i++) {
				S[i] = S[i - 1] + Integer.parseInt(st.nextToken());
			}
			System.out.println(dp(1, K));
		}
	}

}