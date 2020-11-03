package baekjoon.p01194;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N; static int M;
	static char[][] MAP;

	static int getKey(int bag, char key) {
		return bag | (1 << (key - 'a'));
	}

	static boolean canOpen(int bag, char door) {
		return (bag & (1 << (door - 'A'))) > 0;
	}

	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};

	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	static int BFS(int sy, int sx) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {sy, sx, 0});
		boolean[][][] booked = new boolean[N][M][64];
		booked[sy][sx][0] = true;
		int[][][] dist = new int[N][M][64];
		dist[sy][sx][0] = 0;

		while (!q.isEmpty()) {
			int[] here = q.poll();
			int y = here[0]; int x = here[1]; int s = here[2];
			for (int m = 0; m < 4; m++) {
				int ny = y + dy[m]; int nx = x + dx[m];
				if (inRange(ny, nx) && MAP[ny][nx] != '#' && !booked[ny][nx][s]) {
					booked[ny][nx][s] = true;
					dist[ny][nx][s] = dist[y][x][s] + 1;
					if ('a' <= MAP[ny][nx] && MAP[ny][nx] <= 'f') {
						int ns = getKey(s, MAP[ny][nx]);
						q.offer(new int[] {ny, nx, ns});
						booked[ny][nx][ns] = true;
						dist[ny][nx][ns] = dist[y][x][s] + 1;
					} else if ('A' <= MAP[ny][nx] && MAP[ny][nx] <= 'F') {
						if (canOpen(s, MAP[ny][nx])) {
							q.offer(new int[] {ny, nx, s});
						}
					} else {
						q.offer(new int[] {ny, nx, s});
						if (MAP[ny][nx] == '1') {return dist[ny][nx][s];}
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
		int sy = 0; int sx = 0;
		for (int i = 0; i < N; i++) {
			String row = br.readLine();
			for (int j = 0; j < M; j++) {
				MAP[i][j] = row.charAt(j);
				if (MAP[i][j] == '0') {sy = i; sx = j;}
			}
		}
		System.out.println(BFS(sy, sx));
	}

}