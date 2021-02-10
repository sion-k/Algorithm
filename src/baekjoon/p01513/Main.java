package baekjoon.p01513;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	// (y, x)위치에 오락실에 몇번째 오락실이 존재하는지 저장, 없으면 0
	static int[][] MAP;
	static int[][][][] cache;

	static final int MOD = 1000007;

	static boolean inRange(int y, int x) {
		return 1 <= y && y <= N && 1 <= x && x <= M;
	}

	// (y, x)에서 마지막으로 k번째 오락실을 방문했을 때,
	// (N, M)까지 c개의 오락실을 방문하는 경로의 수를 반환
	static int dp(int y, int x, int k, int c) {
		if (y == N && x == M) return c == 0 ? 1 : 0;
		if (cache[y][x][k][c] != -1) return cache[y][x][k][c];
		int sum = 0;
		// 아래로 이동
		if (inRange(y + 1, x)) {
			// 그곳이 오락실인 경우
			if (MAP[y + 1][x] != 0) {
				// 그 전에 아무 오락실도 가지 않았거나, 직전 오락실을 방문했을 때만 가능하다
				if (c > 0 && (k == 0 || k == MAP[y + 1][x] - 1))
					sum = (sum + dp(y + 1, x, MAP[y + 1][x], c - 1)) % MOD;
			} else {
				sum = (sum + dp(y + 1, x, k, c)) % MOD;
			}
		}
		// 오른쪽 이동
		if (inRange(y, x + 1)) {
			// 그곳이 오락실인 경우
			if (MAP[y][x + 1] != 0) {
				// 그 전에 아무 오락실도 가지 않았거나, 직전 오락실을 방문했을 때만 가능하다
				if (c > 0 && (k == 0 || k == MAP[y][x + 1] - 1))
					sum = (sum + dp(y, x + 1, MAP[y][x + 1], c - 1)) % MOD;
			} else {
				sum = (sum + dp(y, x + 1, k, c)) % MOD;
			}
		}
		return cache[y][x][k][c] = sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		MAP = new int[N + 1][M + 1];
		for (int i = 1; i <= C; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			MAP[y][x] = i;
		}
		cache = new int[N + 1][M + 1][C + 1][C + 1];
		for (int i = 0; i < N + 1; i++)
			for (int j = 0; j < M + 1; j++)
				for (int k = 0; k < C + 1; k++)
					Arrays.fill(cache[i][j][k], -1);
		StringBuilder ans = new StringBuilder();
		for (int i = 0; i <= C; i++) {
			// 시작 지점이 오락실인 경우 예외 처리
			if (MAP[1][1] != 0) {
				if (i > 0) {
					ans.append(dp(1, 1, MAP[1][1], i - 1)).append(" ");
				} else {
					ans.append(0).append(" ");
				}
			} else {
				ans.append(dp(1, 1, 0, i)).append(" ");
			}
		}
		System.out.println(ans.toString().trim());
	}

}