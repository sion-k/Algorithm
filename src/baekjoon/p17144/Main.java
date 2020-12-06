package baekjoon.p17144;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N; static int M;
	static int[][] MAP;
	static int[][] NEXTMAP;
	static int[][] filter;

	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};

	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	static void spread(int y, int x) {
		int here = MAP[y][x];
		int there = here / 5;
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d]; int nx = x + dx[d];
			if (!inRange(ny, nx) || NEXTMAP[ny][nx] == -1) {continue;}
			NEXTMAP[ny][nx] += there;
			NEXTMAP[y][x] -= there;
		}
	}

	static void spreadAll() {
		NEXTMAP = new int[N][M];
		NEXTMAP[filter[0][0]][filter[0][1]] =
		NEXTMAP[filter[1][0]][filter[1][1]] = -1;
		for (int y = 0; y < N; y++)
			NEXTMAP[y] = MAP[y].clone();
		for (int y = 0; y < N; y++)
			for (int x = 0; x < M; x++)
				if (MAP[y][x] > 0)
					spread(y, x);
		MAP = NEXTMAP;
	}

	static void circulate() {
		// 공기 청정기 윗쪽
		int uy = filter[0][0]; int ux = filter[0][1];

		// 공기 청정기 아래쪽
		int dy = filter[1][0]; int dx = filter[1][1];


	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		MAP = new int[N][M]; NEXTMAP = new int[N][M];
		int T = Integer.parseInt(st.nextToken());
		int f = 0; filter = new int[2][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				MAP[i][j] = Integer.parseInt(st.nextToken());
				if (MAP[i][j] == -1) {filter[f] = new int[] {i, j}; f++;}
			}
		}
		for (int i = 0; i < T; i++) {
			spreadAll();
			for (int y = 0; y < N; y++)
				System.out.println(Arrays.toString(MAP[y]));
			System.out.println();
		}
	}

}