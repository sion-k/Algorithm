package baekjoon.p02293;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int[] C = new int[N];
		int K = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {C[i] = Integer.parseInt(br.readLine());}
		int[][] cache = new int[2][K + 1];
		cache[0][0] = 1; cache[1][0] = 1;
		for (int j = 1; j <= K; j++) {
			if (j % C[0] == 0) {cache[0][j] = 1;}
		}
		for (int i = 1; i < N; i++) {
			for (int j = 1; j <= K; j++) {
				cache[i % 2][j] = cache[1 - (i % 2)][j];
				if (j - C[i] >= 0) {
					cache[i % 2][j] += cache[i % 2][j - C[i]];
				}
			}
		}
		System.out.println(cache[(N - 1) % 2][K]);
	}

}