package baekjoon.p02228;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] S; static int N;
	static int[][] cache;

	static final int NULL = 987654321;
	static final int NINF = -987654321;

	// S[start, ]에서 정확히 k개의 구간을 선택한 최대 합
	static int dp(int start, int k) {
		if (start >= N) {return k == 0 ? 0 : NINF;}
		if (k == 0) {return 0;}
		if (cache[start][k] != NULL) {return cache[start][k];}
		// 구간을 선택하지 않고 넘어감
		int max = dp(start + 1, k);
		// 구간 선택
		int sum = 0;
		for (int end = start; end < N; end++) {
			sum += S[end];
			max = Math.max(max, sum + dp(end + 2, k - 1));
		}
		return cache[start][k] = max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		S = new int[N]; cache = new int[N][M + 1];
		for (int i = 0; i < N; i++)
			Arrays.fill(cache[i], NULL);
		for (int i = 0; i < N; i++)
			S[i] = Integer.parseInt(br.readLine());
		System.out.println(dp(0, M));
	}

}