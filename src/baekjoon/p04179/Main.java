package baekjoon.p04179;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N; static int M;
	static char[][] MAP;

	static boolean[][] booked;
	static int[][] fireDist;
	static Queue<int[]> q = new LinkedList<>();

	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};

	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	static int BFS(int sy, int sx) {
		if (sy == 0 || sy == N - 1 || sx == 0 || sx == M - 1) {return 1;}
		while (!q.isEmpty()) {
			int[] here = q.poll();
			int y = here[0]; int x = here[1];
			for (int m = 0; m < 4; m++) {
				int ny = y + dy[m]; int nx = x + dx[m];
				if (inRange(ny, nx) && MAP[ny][nx] != '#' && !booked[ny][nx]) {
					q.offer(new int[] {ny, nx});
					booked[ny][nx] = true;
					fireDist[ny][nx] = fireDist[y][x] + 1;
				}
			}
		}
		q.offer(new int[] {sy, sx});
		booked = new boolean[N][M];
		booked[sy][sx] = true;
		int[][] dist = new int[N][M];
		dist[sy][sx] = 0;

		while (!q.isEmpty()) {
			int[] here = q.poll();
			int y = here[0]; int x = here[1];
			for (int m = 0; m < 4; m++) {
				int ny = y + dy[m]; int nx = x + dx[m];
				if (inRange(ny, nx) && MAP[ny][nx] != '#' &&
				dist[y][x] + 1 < fireDist[ny][nx] && !booked[ny][nx]) {
					q.offer(new int[] {ny, nx});
					booked[ny][nx] = true;
					dist[ny][nx] = dist[y][x] + 1;
					if (ny == 0 || ny == N - 1 || nx == 0 || nx == M - 1) {
						return dist[ny][nx] + 1;
					}
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		MAP = new char[N][M];
		booked = new boolean[N][M];
		fireDist = new int[N][M];
		for (int i = 0; i < N; i++) {Arrays.fill(fireDist[i], 1000000);}
		int sy = 0; int sx = 0;
		for (int i = 0; i < N; i++) {
			String row = br.readLine();
			for (int j = 0; j < M; j++) {
				MAP[i][j] = row.charAt(j);
				if (MAP[i][j] == 'J') {sy = i; sx = j;}
				if (MAP[i][j] == 'F') {
					q.offer(new int[] {i, j});
					booked[i][j] = true;
					fireDist[i][j] = 0;
				}
			}
		}
		int ret = BFS(sy, sx);
		if (ret == -1) {System.out.println("IMPOSSIBLE");}
		else {System.out.println(ret);}
	}

}