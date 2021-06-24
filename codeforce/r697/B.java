package codeforce.r697;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class B {
	static int[] cache;

	// n을 2020과 2021의 합으로 표현 가능한지 여부 ? 1 : 0
	static int dp(int n) {
		if (n == 0) return 1;
		if (cache[n] != -1) return cache[n];
		if (n >= 2020 && dp(n - 2020) == 1) return cache[n] = 1;
		if (n >= 2021 && dp(n - 2021) == 1) return cache[n] = 1;
		return cache[n] = 0;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		cache = new int[1000001];
		Arrays.fill(cache, -1);
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			bw.write(dp(N) == 1 ? "YES" : "NO");
			bw.newLine();
		}
		bw.close();
	}

}