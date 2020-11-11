package baekjoon.p17070;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] MAP;
	static int[][][] cache;

	static final int[] dy = { 0, 0, 1, 1, 0, 1,  0,  1, 1 };
	static final int[] dx = { 1, 0, 1, 0, 0, 1,  1,  0, 1 };
	static final int[] ds = { 0, 0, 2, 0, 0, 2, -2, -1, 0 };

	static boolean inRange(int y, int x, int s) {
		return 0 <= y && y < N && 0 <= x && x < N && 0 <= s && s < 3;
	}

	static int[] move(int y, int x, int s, int m) {
		int n = 3 * s + m;
		int ny = y + dy[n]; int nx =  x + dx[n]; int ns = s + ds[n];
		int[] next = new int[] {ny, nx, ns};
		if(!inRange(ny - 1, nx, ns) || !inRange(ny, nx - 1, ns)) {return next;}
		if (m == 2 && (MAP[ny - 1][nx] == 1 || MAP[ny][nx - 1] == 1)) {
			next = new int[] {-1, -1, -1};
		}
		return next;
	}

	static int dp(int y, int x, int s) {
		if (y == N - 1 && x == N - 1) {return 1;}
		if (cache[y][x][s] != -1) {return cache[y][x][s];}
		int sum = 0;
		for (int m = 0; m < 3; m++) {
			int[] there = move(y, x, s, m);
			System.out.println(Arrays.toString(there));
			int ny = there[0]; int nx = there[1]; int ns = there[2];
			if (!inRange(ny, nx, ns) || (nx == x && ny == y && ns == s)) {
				continue;
			}
			sum += dp(ny, nx, ns);
		}
		return cache[y][x][s] = sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		MAP = new int[N][N];
		cache = new int[N][N][3];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				Arrays.fill(cache[i][j], -1);
				MAP[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(dp(0, 1, 0));
	}

}