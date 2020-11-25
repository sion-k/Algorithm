package baekjoon.p09376;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N; static int M;
	static char[][] MAP;
	static int[][][] DIST;

	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};

	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	static void BFS(int sy, int sx, int p) {
		Deque<int[]> q = new LinkedList<>();
		q.offer(new int[] {sy, sx});
		int[][] dist = new int[N][M];
		for (int i = 0; i < N; i++) {Arrays.fill(dist[i], -1);}
		dist[sy][sx] = 0;
		while (!q.isEmpty()) {
			int[] here = q.poll();
			int y = here[0]; int x = here[1];
			for (int m = 0; m < 4; m++) {
				int ny = y + dy[m]; int nx = x + dx[m];
				if (!inRange(ny, nx) || MAP[ny][nx] == '*' || dist[ny][nx] != -1) {continue;}
				if (MAP[ny][nx] == '.') {
					dist[ny][nx] = dist[y][x];
					q.offerFirst(new int[] {ny, nx});
				} else if (MAP[ny][nx] == '#') {
					dist[ny][nx] = dist[y][x] + 1;
					q.offerLast(new int[] {ny, nx});
				}
			}
		}
		DIST[p] = dist;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()) + 2;
			M = Integer.parseInt(st.nextToken()) + 2;
			MAP = new char[N][M];
			DIST = new int[3][N][M];
			List<int[]> prisoner = new ArrayList<>();
			for (int i = 0; i < N; i++) {Arrays.fill(MAP[i], '.');}
			for (int i = 1; i < N - 1; i++) {
				String row = br.readLine();
				for (int j = 1; j < M - 1; j++) {
					MAP[i][j] = row.charAt(j - 1);
					if (MAP[i][j] == '$') {prisoner.add(new int[] {i, j});}
				}
			}
			for (int i = 0; i < 2; i++) {
				int[] p = prisoner.get(i);
				BFS(p[0], p[1], i);
			}
			BFS(0, 0, 2);
			for (int k = 1; k < 3; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < M; j++) {
						DIST[0][i][j] += (DIST[k][i][j]);
					}
				}
			}
			int min = 10000;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (MAP[i][j] == '*') {continue;}
					min = Math.min(min, DIST[0][i][j] + (MAP[i][j] == '#' ? -2 : 0));
				}
			}
			bw.write(String.valueOf(min));
			bw.newLine();
		}
		bw.close();
	}

}