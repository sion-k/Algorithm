package baekjoon.p01699;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			int min = i;
			for (int j = 1; j * j <= i; j++)
				min = Math.min(min, 1 + dp[i - j * j]);
			dp[i] = min;
		}
		System.out.println(dp[N]);
	}

}