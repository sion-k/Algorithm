package baekjoon.p03055;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static char[][] MAP;
	static boolean[][][] BOOKED;
	static int[][] DIST;

	static int gy; static int gx;

	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};

	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	// 최단 거리 반환. 도달 불가능하면 -1 반환
	static int BFS(int sy, int sx) {
		Queue<int[]> q = new LinkedList<>();
		BOOKED = new boolean[N][M][2];
		DIST = new int[N][M];
		// 물을 먼저 큐에 추가
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (MAP[i][j] == '*') {
					q.offer(new int[] {i, j, 1});
					BOOKED[i][j][1] = true;
				}
			}
		}
		// 고슴도치를 큐에 추가
		q.offer(new int[] {sy, sx, 0});
		BOOKED[sy][sx][0] = true;
		DIST[sy][sx] = 0;

		while(!q.isEmpty()) {
			int[] here = q.poll();
			int y = here[0]; int x = here[1]; int c = here[2];
			for (int next = 0; next < 4; next++) {
				int ty = y + dy[next]; int tx = x + dx[next];
				if(inRange(ty, tx) && MAP[ty][tx] != 'X' && !BOOKED[ty][tx][c]) {
					if (c == 0) {
						DIST[ty][tx] = DIST[y][x] + 1;
						if (ty == gy && tx == gx) {return DIST[ty][tx];}
						if (BOOKED[ty][tx][1]) {continue;}
					} else {
						if (MAP[ty][tx] == 'D') {continue;}
					}
					q.offer(new int[] {ty, tx, c});
					BOOKED[ty][tx][c] = true;
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		MAP = new char[N][M];
		int sy = 0; int sx = 0;
		for (int i = 0; i < N; i++) {
			String row = br.readLine();
			for (int j = 0; j < M; j++) {
				MAP[i][j] = row.charAt(j);
				if (MAP[i][j] == 'D') {gy = i; gx = j;}
				if (MAP[i][j] == 'S') {
					sy = i; sx = j;
					MAP[i][j] = '.';
				}
			}
		}
		int ret = BFS(sy, sx);
		if (ret != -1) {bw.write(String.valueOf(ret));}
		else {bw.write("KAKTUS");}
		bw.newLine();
		bw.close();
	}

}