package baekjoon.p02688;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static long[][] cache;

	static long dp(int n, int d) {
		if (n == 1) {return 10 - d;}
		if (cache[n][d] != 0) {return cache[n][d];}
		for (int ch = d; ch <= 9; ch++) {cache[n][d] += dp(n - 1, ch);}
		return cache[n][d];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		cache = new long[65][10];
		for (int c = 0; c < T; c++) {
			int N = Integer.parseInt(br.readLine());
			bw.write(String.valueOf(dp(N, 0)));
			bw.newLine();
		}
		bw.close();
	}

}