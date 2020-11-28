package baekjoon.p02169;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {
	static final int NINF = -987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] MAP = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				MAP[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[][][] dp = new int[3][N][M];
		// 기저 사례 : 오른쪽 맨 아래
		dp[0][N - 1][M - 1] = dp[1][N - 1][M - 1] = dp[2][N - 1][M - 1] =
		MAP[N - 1][M - 1];
		// 맨 아래 줄 채우기
		// m = 1 왼쪽으로만 움직일 수 있을 때
		for (int x = 0; x < M - 1; x++)
			dp[1][N - 1][x] = NINF;
		// m = 2 오른쪽으로만 움직일 수 있을 때
		for (int x = M - 2; x >= 0; x--)
			dp[2][N - 1][x] = dp[2][N - 1][x + 1] + MAP[N - 1][x];
		// m = 0 양쪽으로 움직일 수 있을 때
		// x = 0인경우 예외, M이 1인 경우도 예외
		if (M >= 2) dp[0][N - 1][0] += dp[2][N - 1][1] + MAP[N - 1][0];
		for (int x = 1; x < M - 1; x++)
			dp[0][N - 1][x] = Math.max(dp[1][N - 1][x - 1], dp[2][N - 1][x + 1]) +
			MAP[N - 1][x];
		// x = M - 1인경우는 기저사례에서 처리됨
		// 맨 아래 줄 채우기 끝
		// 맨 아래 줄이 있으면 나머지를 채울 수 있음
		for (int y = N - 2; y >= 0; y--) {
			// m = 1
			// x = 0인 경우 예외
			dp[1][y][0] = dp[0][y + 1][0] + MAP[y][0];
			for (int x = 1; x < M; x++)
				dp[1][y][x] = Math.max(dp[0][y + 1][x], dp[1][y][x - 1]) +
				MAP[y][x];
			// m = 2
			// x = M - 1인 경우 예외
			dp[2][y][M - 1] = dp[0][y + 1][M - 1] + MAP[y][M - 1];
			for (int x = M - 2; x >= 0; x--)
				dp[2][y][x] = Math.max(dp[0][y + 1][x], dp[2][y][x + 1]) +
				MAP[y][x];
			// m = 1, 2가 있으면 m = 0 채우기 가능
			// x = 0인 경우 예외, M이 1인 경우도 예외
			dp[0][y][0] = Math.max(dp[0][y + 1][0], M >= 2 ? dp[2][y][1] : NINF) +
			MAP[y][0];
			for (int x = 1; x < M - 1; x++)
				dp[0][y][x] = Math.max(dp[0][y + 1][x],
				Math.max(dp[1][y][x - 1], dp[2][y][x + 1])) +
				MAP[y][x];
			// x = M - 1인 경우 예외, M이 1인 경우도 예외
			dp[0][y][M - 1] = Math.max(dp[0][y + 1][M - 1], M >= 2 ? dp[1][y][M - 2] : NINF) +
			MAP[y][M - 1];
		}
		System.out.println(dp[0][0][0]);
	}

}