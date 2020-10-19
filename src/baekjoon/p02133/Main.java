package baekjoon.p02133;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	static int N;
	static int[][] cache;

	// k : 0  튀어나온 것 없음 k : 1 튀어나왔음
	static int dp(int i, int k) {
		if (i == N) {return k == 0 ? 1 : 0;}
		if (cache[i][k] != -1) {return cache[i][k];}
		if (k ==0) {
			cache[i][k] = 2 * dp(i + 1, 1);
			if (i + 2 <= N) {cache[i][k] += dp(i + 2, 0);}
		} else {
			cache[i][k] = dp(i + 1, 0);
			if (i + 2 <= N) {cache[i][k] += dp(i + 2, 1);}
		}
		return cache[i][k];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		cache = new int[N][2];
		int s = 0; if (N % 2 == 0) {s++;}
		for (int i = 0; i < N; i++) {Arrays.fill(cache[i], -1);}
		while (s < N) {cache[s][0] = 0; s += 2;}
		bw.write(String.valueOf(dp(0, 0)));
		bw.newLine();
		br.close();
		bw.close();
	}

}