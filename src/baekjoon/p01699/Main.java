package baekjoon.p01699;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int[] cache;
	static int[][] cache2;

	static int dp(int N) {
		double sqrt = Math.sqrt(N);
		if (Double.compare(sqrt, (int)sqrt) == 0) {return 1;}
		if (cache[N] != 0) {return cache[N];}
		int min = N;
		for (int i = 1; i * i <= N; i++) {
			min = Math.min(min, dp(N - i * i));
		}
		return cache[N] = 1 + min;
	}

	static int dp2(int N, int K) {
		double sqrt = Math.sqrt(N);
		if (Double.compare(sqrt, (int)sqrt) == 0) {return 1;}
		if (cache2[N][K] != 0) {return cache[N];}
		int min = N;
		for (int i = 1; i * i <= K; i++) {
			min = Math.min(min, dp2(N - i * i, i * i));
		}
		return cache2[N][K] = 1 + min;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		cache = new int[N + 1];
		cache2 = new int[N + 1][(int)Math.sqrt(N) + 1];
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(String.valueOf(dp(N)));
		bw.newLine();
		bw.write(String.valueOf(dp2(N, (int)Math.sqrt(N))));
		br.close();
		bw.close();
	}

}