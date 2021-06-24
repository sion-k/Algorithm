package baekjoon.p11057;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[][] cache;

	// 이전에 d를 골랐을 때 N개의 길이의 가능한 오르막 수
	static int dp(int n, int d) {
		if (n == 0) {return 1;}
		if (cache[n][d] != 0) { return cache[n][d];}
		int sum = 0;
		for (int pick = d; pick <= 9; pick++) {
			sum = (sum + dp(n - 1, pick)) % 10007;
		}
		return cache[n][d] = sum;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		br.close();
		cache = new int[N + 1][10];
		System.out.println(dp(N, 0));
	}

}