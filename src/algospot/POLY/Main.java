package algospot.POLY;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int[][] cache;

	static final int MOD = 10000000;

	static int dp(int n, int k) {
		if (n == 0) {return 1;}
		if (cache[n][k] != 0) {return cache[n][k];}
		int sum = 0;
		for (int pick = 1; pick <= n; pick++) {
			sum = (sum + (pick + k - 1) * dp(n - pick, pick)) % MOD;
		}
		return cache[n][k] = sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		cache = new int[101][101];
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			int N = Integer.parseInt(br.readLine());
			int ret = 0;
			for (int i = 1; i <= N; i++) {
				ret = (ret + dp(N - i, i)) % MOD;
			}
			bw.write(String.valueOf(ret));
			bw.newLine();
		}
		bw.close();
	}

}