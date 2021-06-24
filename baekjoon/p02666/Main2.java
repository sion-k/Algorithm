package baekjoon.p02666;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {
	static int L; // 벽장문의 길이
	static int N; static int[] S;
	static int[][] cache;

	// 마지막으로 i, j번째 사용하는 벽장문을 열어놨을 때, 최소 이동 횟수
	static int dp(int i, int j) {
		int next = Math.max(i, j) + 1;
		// 둘중 하나라도 마지막 벽장문을 사용하면 끝
		if (next == N) {return 0;}
		if (cache[i][j] != -1) {return cache[i][j];}
		if (S[i] == S[next]) return cache[i][j] = dp(next, j);
		if (S[j] == S[next]) return cache[i][j] = dp(i, next);
		// 왼쪽, 오른쪽 빈 벽장문을 이동시키는 경우
		return cache[i][j] =
		Math.min(Math.abs(S[next] - S[i]) + dp(next, j),
				 Math.abs(S[next] - S[j]) + dp(i, next));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		L = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(br.readLine()) + 2;
		S = new int[N];
		S[0] = a - 1; S[1] = b - 1;
		for (int i = 2; i < N; i++)
			S[i] = Integer.parseInt(br.readLine()) - 1;
		cache = new int[N][N];
		for (int i = 0; i < N; i++)
			Arrays.fill(cache[i], -1);
		System.out.println(dp(0, 1));
	}

}