package baekjoon.p02225;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] cache;

	static int dp(int S, int K) {
		if (K == 1 || S == 0) {return 1;}
		if (cache[S][K] != 0) {return cache[S][K];}
		int sum = 0;
		for (int pick = 0; pick <=S; pick++) {
			sum = (sum + dp(S - pick, K - 1)) % 1000000000;
		}
		return cache[S][K] = sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		cache = new int[N + 1][K + 1];
		System.out.println(dp(N, K));
	}

}