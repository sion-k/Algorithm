package codeforce.r697;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class E {
	static int N;
	static int[] S;
	static int[][][] cache;
	static final int MOD = 1000000009;

	// i번째 원소부터 toPick개를 고를 때, 앞으로의 합이 k이 되도록 고르는 경우의 수
	static int dp(int i, int toPick, int k) {
		if (i >= N) {
			if (toPick == 0 && k == 0) return 1;
			return 0;
		}
		if (cache[i][toPick][k] != -1) return cache[i][toPick][k];
		if (toPick == 0) return cache[i][toPick][k] = k == 0 ? 1 : 0;
		long sum = dp(i + 1, toPick, k);
		if (k - S[i] >= 0)
			sum = (sum + dp(i + 1, toPick - 1, k - S[i])) % MOD;
		return cache[i][toPick][k] = (int)sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine(), " ");
			S = new int[N];
			for (int i = 0; i < N; i++)
				S[i] = Integer.parseInt(st.nextToken());
			Arrays.sort(S);
			int max = 0;
			for (int i = 0; i < K; i++) max += S[N - i - 1];
			cache = new int[N][K + 1][max + 1];
			for (int i = 0; i < N; i++)
				for (int j = 0; j < K + 1; j++)
					Arrays.fill(cache[i][j], -1);
			bw.write(String.valueOf(dp(0, K, max)));
			bw.newLine();
		}
		bw.close();
	}

}