package baekjoon.p01563;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int[][][] cache;
	static final int MOD = 1000000;

	// i번째 일까지 지각 p번 연속으로 q번 결석 했을 때, 경우의 수
	static int dp(int i, int p, int q) {
		if (i >= N) return 1;
		if (cache[i][p][q] != 0) return cache[i][p][q];
		// 출석하는 경우, 연속 결석은 초기화 됨
		int sum = dp(i + 1, p , 0);
		// 지각 가능하면 지각
		if (p + 1 < 2) sum = (sum + dp(i + 1, p + 1, 0)) % MOD;
		// 결석 가능하면 결석
		if (q + 1 < 3) sum = (sum + dp(i + 1, p, q + 1)) % MOD;
		return cache[i][p][q] = sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cache = new int[N][2][3];
		System.out.println(dp(0, 0, 0));
	}

}