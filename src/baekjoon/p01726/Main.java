package baekjoon.p01726;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N; static int M;
	static boolean[][] MAP; // 이동할 수 있는지 여부

	static int gy; static int gx; static int gd;

	// [1, 4] 방향을 바라볼 때의 목적지 상대 좌표 (0은 무시)
	static final int[] dy = { 0, 0, 0, 1, -1 };
	static final int[] dx = { 0, 1, -1, 0, 0 };

	static final int[] dl = {0, 4, 3, 1, 2};
	static final int[] dr = {0, 3, 4, 2, 1};

	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	// [1, 4] = 동서남북
	static int BFS(int sy, int sx, int sd) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][][] BOOKED = new boolean[N][M][5];
		int[][][] DIST = new int[N][M][5];
		q.offer(new int[] {sy, sx, sd});
		BOOKED[sy][sx][sd] = true;
		DIST[sy][sx][sd] = 0;
		if (sy == gy && sx == gx && sd == gd) {return DIST[sy][sx][sd];}

		while (!q.isEmpty()) {
			int[] here = q.poll();
			int y = here[0]; int x = here[1]; int d = here[2];
			// Go k [1, 3]
			for (int i = 1; i <= 3; i++) {
				int ty = y + i * dy[d]; int tx = x + i * dx[d];
				if (inRange(ty, tx) && !BOOKED[ty][tx][d]) {
					// 경로중에 하나라도 1이면 그 후에 위치 못감
					if (!MAP[ty][tx]) {break;}
					q.offer(new int[] {ty, tx, d});
					BOOKED[ty][tx][d] = true;
					DIST[ty][tx][d] = DIST[y][x][d] + 1;
					if (ty == gy && tx == gx && d == gd) {
						return DIST[ty][tx][d];
					}
				}
			}
			// Turn left
			int ld = dl[d];
			if (!BOOKED[y][x][ld]) {
				q.offer(new int[] {y, x, ld});
				BOOKED[y][x][ld] = true;
				DIST[y][x][ld] = DIST[y][x][d] + 1;
				if (y == gy && x == gx && ld == gd) {
					return DIST[y][x][ld];
				}
			}
			// Turn right
			int rd = dr[d];
			if (!BOOKED[y][x][rd]) {
				q.offer(new int[] {y, x, rd});
				BOOKED[y][x][rd] = true;
				DIST[y][x][rd] = DIST[y][x][d] + 1;
				if (y == gy && x == gx && rd == gd) {
					return DIST[y][x][rd];
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
		MAP = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				MAP[i][j] = st.nextToken().equals("0");
			}
		}
		st = new StringTokenizer(br.readLine(), " ");
		int y = Integer.parseInt(st.nextToken()) - 1;
		int x = Integer.parseInt(st.nextToken()) - 1;
		int d = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine(), " ");
		gy = Integer.parseInt(st.nextToken()) - 1;
		gx = Integer.parseInt(st.nextToken()) - 1;
		gd = Integer.parseInt(st.nextToken());

		System.out.println(BFS(y, x, d));
	}

}