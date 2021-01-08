package baekjoon.p15486;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] S = new int[N + 1][2];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int T = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken());
			S[i][0] = T; S[i][1] = P;
		}
		int[] dp = new int[N + 2];
		for (int i = N - 1; i >= 1; i--) {
			int max = 0;
			// 상담을 선택할 수 있는 경우 선택
			if (i + S[i][0] <= N + 1)
				max = Math.max(max, S[i][1] + dp[i + S[i][0]]);
			// 선택하지 않는 경우
			max = Math.max(max, dp[i + 1]);
			dp[i] = max;
		}
		System.out.println(dp[1]);
	}

}