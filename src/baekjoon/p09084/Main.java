package baekjoon.p09084;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			int N = Integer.parseInt(br.readLine());
			int[] C = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				C[i] = Integer.parseInt(st.nextToken());
			}
			int M = Integer.parseInt(br.readLine());
			int[][] cache = new int[2][M + 1];
			cache[0][0] = 1; cache[1][0] = 1;
			for (int j = 1; j <= M; j++) {
				cache[0][j] = j % C[0] == 0 ? 1 : 0;
			}
			for (int i = 1; i < N; i++) {
				for (int j = 1; j <= M; j++) {
					cache[i % 2][j] = cache[1 - i % 2][j];
					if (j - C[i] >= 0) {
						cache[i % 2][j] += cache[i % 2][j - C[i]];
					}
				}
			}
			System.out.println(cache[(N - 1) % 2][M]);
		}
	}

}