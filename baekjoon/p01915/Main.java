package baekjoon.p01915;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N; static int M;
	static int[][] MAP;
	static int[][] cache;

	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	static int dp(int y, int x) {
		if (!inRange(y, x)) {return 0;}
		if (MAP[y][x] == 0) {return 0;}
		if (cache[y][x] != -1) {return cache[y][x];}
		int min = dp(y + 1, x + 1);
		min = Math.min(min, dp(y, x + 1));
		min = Math.min(min, dp(y + 1, x));
		return cache[y][x] = 1 + min;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cache = new int[N][M]; MAP = new int[N][M];
		for (int i = 0; i < N; i++) {
			Arrays.fill(cache[i], -1);
			String row = br.readLine();
			for (int j = 0; j < M; j++) {
				MAP[i][j] = (row.charAt(j) - '0');
			}
		}
		int max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				max = Math.max(max, dp(i, j));
			}
		}
		System.out.println((int)(Math.pow(max, 2)));
	}
}