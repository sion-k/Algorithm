package baekjoon.p02169;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {

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
		// 맨아래 왼쪽, 맨 아래 오른쪽 채우기
		dp[1][N - 1][0] = MAP[N - 1][0];
		dp[2][N - 1][M - 1] = MAP[N - 1][M - 1];
		// 맨 아래 줄 채우기
		// m = 1 왼쪽으로 움직일 수 있는 경우
		for (int x = 1; x < M ; x++) {
			dp[1][N - 1][x] = dp[1][N - 1][x - 1] + MAP[N - 1][x];
		}
		// m = 2 오른쪽으로 움직일 수 있는 경우
		for (int x = M - 2; x >= 0; x--) {
			dp[2][N - 1][x] = dp[2][N - 1][x + 1] + MAP[N - 1][x];
		}
		// m = 0 양쪽으로 움직일 수 있는 경우
		dp[0][N - 1][0] = MAP[N - 1][0] + dp[2][N - 1][1];
		for (int x = 1; x < M - 1; x++) {
			dp[0][N - 1][x] = MAP[N - 1][x] +
			Math.max(dp[1][N - 1][x - 1], dp[2][N - 1][x + 1]);
		}
		dp[0][N - 1][M - 1] = MAP[N - 1][M - 1] + dp[1][N - 1][M - 2];
		for (int m = 0; m < 3; m++) {
			for (int i = 0; i < N; i++) {
				System.out.println(Arrays.toString(dp[m][i]));
			}
			System.out.println();
		}
		// 전체 채우기
		for (int y = N - 2; y >= 0; y--) {
			// m = 1 왼쪽으로 움직일 수 있는 경우
			dp[1][y][0] = MAP[y][0] + dp[0][y + 1][0];
			for (int x = 1; x < M ; x++) {
				dp[1][y][x] = MAP[y][x] +
				Math.max(dp[1][y][x - 1], dp[0][y + 1][x]);
			}
			// m = 2 오른쪽으로 움직일 수 있는 경우
			dp[2][y][M - 1] = MAP[y][M - 1] + dp[0][y + 1][M - 1];
			for (int x = M - 2; x >= 0; x--) {
				dp[2][y][x] = MAP[y][x] +
				Math.max(dp[2][y][x + 1], dp[0][y + 1][x]);
			}
			// m = 0 양쪽으로 움직일 수 있는 경우
			dp[0][y][0] = MAP[y][0] + Math.max(dp[2][y][1], dp[0][y + 1][0]);
			for (int x = 1; x < M - 1; x++) {
				dp[0][y][x] = MAP[y][x] +
				Math.max(Math.max(dp[1][y][x - 1], dp[0][y][x + 1]), dp[0][y + 1][x]);
			}
			dp[0][y][M - 1] = MAP[y][M - 1] +
			Math.max(dp[1][y][M - 2], dp[0][y + 1][M - 1]);
		}
		System.out.println(dp[0][0][0]);
	}

}