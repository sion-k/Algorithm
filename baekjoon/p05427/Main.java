package baekjoon.p05427;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N; static int M;
	static boolean[][] MAP;
	static Queue<int[]> Q;
	static boolean[][] BOOKED;
	static int[][] FIREDIST;

	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};
	static final int INF = 1000001;

	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	static int BFS(int sy, int sx) {
		if (sy == 0 || sx == 0 || sy == N - 1 || sx == M - 1) {return 1;}
		// 불 먼저 이동
		while (!Q.isEmpty()){
			int[] here = Q.poll();
			int y = here[0]; int x = here[1];
			for (int m = 0; m < 4; m++) {
				int ny = y + dy[m]; int nx = x + dx[m];
				if (inRange(ny, nx) && MAP[ny][nx] && !BOOKED[ny][nx]) {
					Q.offer(new int[] {ny, nx});
					BOOKED[ny][nx] = true;
					FIREDIST[ny][nx] = FIREDIST[y][x] + 1;
				}
			}
		}
		Q.offer(new int[] {sy, sx});
		BOOKED = new boolean[N][M];
		BOOKED[sy][sx] = true;
		int[][] dist = new int[N][M];
		dist[sy][sx] = 0;
		// 사람 이동
		while (!Q.isEmpty()){
			int[] here = Q.poll();
			int y = here[0]; int x = here[1];
			for (int m = 0; m < 4; m++) {
				int ny = y + dy[m]; int nx = x + dx[m];
				if (inRange(ny, nx) && MAP[ny][nx] && !BOOKED[ny][nx] && dist[y][x] + 1 < FIREDIST[ny][nx]) {
					Q.offer(new int[] {ny, nx});
					BOOKED[ny][nx] = true;
					dist[ny][nx] = dist[y][x] + 1;
					if (ny == 0 || nx == 0 || ny == N - 1 || nx == M - 1) {
						return dist[ny][nx] + 1;
					}
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			MAP = new boolean[N][M];
			Q = new LinkedList<>();
			BOOKED = new boolean[N][M];
			FIREDIST = new int[N][M];
			for (int i = 0; i < N; i++) {Arrays.fill(FIREDIST[i], INF);}

			int y = 0; int x = 0;
			for (int i = 0; i < N; i++) {
				String row = br.readLine();
				for (int j = 0; j < M; j++) {
					MAP[i][j] = (row.charAt(j) != '#');
					if (row.charAt(j) == '*') {
						Q.offer(new int[] {i, j});
						BOOKED[i][j] = true;
						FIREDIST[i][j] = 0;
					}
					if (row.charAt(j) == '@') {y = i; x = j;}
				}
			}

			int ret = BFS(y, x);
			if (ret == -1) {
				bw.write("IMPOSSIBLE");
			} else {
				bw.write(String.valueOf(ret));
			}
			bw.newLine();
		}
		bw.close();
	}

}