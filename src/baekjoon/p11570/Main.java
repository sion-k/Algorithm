package baekjoon.p11570;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N; static int[] S;
	static int[][] cache;

	// 마지막으로 i j번째 음을 불렀을 때 노래할 때 힘들 수 있는 정도의 최솟값
	static int dp(int i, int j) {
		// 다 부른 경우
		if (i == N - 1 || j == N - 1) {return 0;}
		// 힘든 정도는 0일 수도 있음
		if (cache[i][j] != -1) {return cache[i][j];}
		// 다음으로 불러야 할 음
		int next = Math.max(i, j) + 1;
		int cost1 = Math.abs(S[next] - S[i]);
		int cost2 = Math.abs(S[next] - S[j]);
		return cache[i][j] =
		Math.min(cost1 + dp(next, j), cost2 + dp(i, next));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); S = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			S[i] = Integer.parseInt(st.nextToken());
		}
		cache = new int[N][N];
		for (int i = 0; i < N; i++)
			Arrays.fill(cache[i], -1);
		int min = Integer.MAX_VALUE;
		for (int j = 1; j < N; j++)
			System.out.println(dp(0, j));
		for (int i = 1; i < N; i++)
			System.out.println(dp(i, 0));
		System.out.println(min);
	}

}