package baekjoon.p01937;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] BOARD;
	static int[][] cache;

	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};

	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}

	static int dp(int y, int x) {
		if (cache[y][x] != 0) {return cache[y][x];}
		int max = 0;
		for (int next = 0; next < 4; next++) {
			int ty = y + dy[next]; int tx = x + dx[next];
			if (inRange(ty, tx) && BOARD[ty][tx] > BOARD[y][x]) {
				max = Math.max(max, dp(ty, tx));
			}
		}
		return cache[y][x] = 1 + max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N =Integer.parseInt(st.nextToken());
		cache = new int[N][N]; BOARD = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				BOARD[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int max = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				max = Math.max(max, dp(i, j));
			}
		}
		System.out.println(max);
	}
}