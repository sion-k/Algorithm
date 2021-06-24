package baekjoon.p02169;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N; static int M;
	static int[][] MAP;
	static int[][][] cache;
	static final int NINF = -987654321;

	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	// m == 0 : 왼쪽 오른쪽 둘다 이동가능
	// m == 1 : 왼쪽만 m == 2 : 오른쪽만
	static int dp(int m, int y, int x) {
		if (y == N - 1 && x == M - 1) {return MAP[y][x];}
		if (cache[m][y][x] != Integer.MIN_VALUE) {return cache[m][y][x];}
		int max = NINF;
		if ((m == 0 || m == 1) && inRange(y, x - 1))
			max = Math.max(max, dp(1, y, x - 1));
		if ((m == 0 || m == 2) && inRange(y, x + 1))
			max = Math.max(max, dp(2, y, x + 1));
		if (inRange(y + 1, x))
			max = Math.max(max, dp(0, y + 1, x));
		return cache[m][y][x] = MAP[y][x] + max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M  = Integer.parseInt(st.nextToken());
		MAP = new int[N][M];
		cache = new int[3][N][M];
		for (int k = 0; k < 3; k++)
			for (int i = 0; i < N; i++)
				Arrays.fill(cache[k][i], Integer.MIN_VALUE);
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				MAP[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(dp(0, 0, 0));
	}

}