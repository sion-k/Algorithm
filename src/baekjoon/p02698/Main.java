package baekjoon.p02698;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][][] cache;

	static int dp(int p, int n, int k) {
		if (n == 0) {return k == 0 ? 1 : 0;}
		if (n < k) {return 0;}
		if (cache[p][n][k] != -1) {return cache[p][n][k];}
		int sum = dp(0, n - 1, k);
		if (p == 0) sum += dp(1, n - 1, k);
		else if (k > 0) sum += dp(1, n - 1, k - 1);
		return cache[p][n][k] = sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		cache = new int[2][101][101];
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 101; j++)
				Arrays.fill(cache[i][j], -1);
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			bw.write(String.valueOf(dp(0, n, k)));
			bw.newLine();
		}
		bw.close();
	}

}