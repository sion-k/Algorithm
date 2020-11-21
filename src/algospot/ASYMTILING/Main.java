package algospot.ASYMTILING;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int[] cache;

	static final int MOD = 1000000007;

	static int dp(int n) {
		if (n == 0) {return 1;}
		if (cache[n] != 0) {return cache[n];}
		cache[n] = (cache[n] + dp(n - 1)) % MOD;
		if (n >= 2) {
			cache[n] = (cache[n] + dp(n - 2)) % MOD;
		}
		return cache[n];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		cache = new int[101];
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			int N = Integer.parseInt(br.readLine());
			int ret = dp(N);
			if (N % 2 == 0) {
				ret = (ret - dp(N / 2) + MOD) % MOD;
				ret = (ret - dp(N / 2 - 1) + MOD) % MOD;
			} else {
				ret = (ret - dp(N / 2) + MOD) % MOD;
			}
			bw.write(String.valueOf(ret));
			bw.newLine();
		}
		bw.close();
	}

}