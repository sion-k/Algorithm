package baekjoon.p13302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static boolean[] notUse;
	static int[][] cache;

	// i번째 일에 쿠폰이 c개 있을 때, 리조트에 모두 입장하기 위한 최소 비용
	static int dp(int i, int c) {
		if (i >= N) return 0;
		if (cache[i][c] != -1) return cache[i][c];
		if (notUse[i]) return cache[i][c] = dp(i + 1, c);
		// 하루 이용권을 구매하는 경우
		int min = 10000 + dp(i + 1, c);
		// 연속 3일권을 구매하는 경우
		min = Math.min(min, 25000 + dp(i + 3, c + 1));
		// 연속 5일권을 구매하는 경우
		min = Math.min(min, 37000 + dp(i + 5, c + 2));
		// 쿠폰을 사용할 수 있는 경우 사용
		if (c >= 3) min = Math.min(min, dp(i + 1, c - 3));
		return cache[i][c] = min;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		if (M > 0) st = new StringTokenizer(br.readLine(), " ");
		notUse = new boolean[N];
		for (int i = 0; i < M; i++)
			notUse[Integer.parseInt(st.nextToken()) - 1] = true;
		cache = new int[N][N];
		for (int i = 0; i < N; i++)
			Arrays.fill(cache[i], -1);
		System.out.println(dp(0, 0));
	}

}