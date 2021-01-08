package baekjoon.p15486;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] S;
	static int[] cache;

	// [i, ) 이후로 얻을 수 있는 최대 수익 (1-based)
	static int dp(int i) {
		if (i >= N + 1) return 0;
		if (cache[i] != -1) return cache[i];
		int max = 0;
		// 상담을 선택할 수 있는 경우 선택
		if (i + S[i][0] - 1 <= N)
			max = Math.max(max, S[i][1] + dp(i + S[i][0]));
		// 상담을 선택하지 않는 경우
		max = Math.max(max, dp(i + 1));
		return cache[i] = max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		S = new int[N + 1][2];
		cache = new int[N + 1];
		Arrays.fill(cache, -1);
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int T = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken());
			S[i][0] = T; S[i][1] = P;
		}
		System.out.println(dp(1));
	}

}