package baekjoon.p15988;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	static int[] cache;
	static final int MOD = 1000000009;

	// N을 1, 2, 3의 합으로 나타내는 방법의 수 반환
	static int dp(int N) {
		if (N < 0) {return 0;}
		if (N == 0) {return 1;}
		if (cache[N] != -1) {return cache[N];}
		int sum = 0;
		for (int i = 1; i <= 3; i++) {sum = (sum + dp(N - i)) % MOD;}
		return cache[N] = sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		cache = new int[1000001];
		Arrays.fill(cache, -1);
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			int N = Integer.parseInt(br.readLine());
			bw.write(String.valueOf(dp(N)));
			bw.newLine();
		}
		bw.close();
	}

}