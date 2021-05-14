package baekjoon.p14500;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N; static int M;

	static final int[][][] dy = {
	{ { 0, 1, 2, 3 }, { 0, 0, 0, 0 } },
	{ { 0, 0, 1, 1 } },
	{ { 0, 1, 2, 2 }, { 0, 1, 2, 2 }, { 0, 0, 1, 2 }, { 0, 0, 1, 2 },
		{ 0, 1, 1, 1, }, { 0, 1, 1, 1 }, { 0, 0, 0, 1 }, { 0, 0, 0, 1 }
	},
	{ { 0, 1, 1, 2 }, { 0, 1, 1, 2 }, { 0, 0, 1, 1 }, { 0, 0, 1, 1 } },
	{ { 0, 0, 0, 1 }, { 0, 1, 1, 1 }, { 0, 1, 1, 2 }, { 0, 1, 1, 2 } }
	};

	static final int[][][] dx = {
	{ { 0, 0, 0, 0 }, { 0, 1, 2, 3 } },
	{ { 0, 1, 0, 1 } },
	{ { 0, 0, 0, 1 }, { 0, 0, 0, -1 }, { 0, 1, 0, 0 }, { 0, 1, 1, 1 },
		{ 0, -2, -1, 0 }, { 0, 0, 1, 2 }, { 0, 1, 2, 2 }, { 0, 1, 2, 0 }
	},
	{ { 0, 0, 1, 1 }, { 0, 0, -1, -1 }, { 0, 1, -1, 0 }, { 0, 1, 1, 2 } },
	{ { 0, 1, 2, 1 }, { 0, -1, 0, 1 }, { 0, -1, 0, 0 }, { 0, 0, 1, 0 } }
	};

	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int max = 0;
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				for (int k = 0; k < dy.length; k++) {
					for (int v = 0; v < dy[k].length; v++) {
						int sum = 0;
						for (int m = 0; m < dy[k][v].length; m++) {
							int ny = y + dy[k][v][m]; int nx = x + dx[k][v][m];
							if (!inRange(ny, nx)) {sum = 0; break;}
							sum += map[ny][nx];
						}
						max = Math.max(max, sum);
					}
				}
			}
		}
		System.out.println(max);
	}

}