package baekjoon.p01600;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N; static int M;
	static int K;
	static boolean[][] MOVABLE;
	static boolean[][][] BOOKED;
	static int[][][] DIST;

	// 상하좌우 이동
	static final int[] dy1 = { -1, 1, 0, 0 };
	static final int[] dx1 = { 0, 0, -1, 1 };

	// 말 처럼 이동
	static final int[] dy2 = { -2, -2, -1, -1, 1, 1, 2, 2 };
	static final int[] dx2 = { -1, 1, -2, 2, -2, 2, -1, 1 };

	//[0, N)
	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	// (0, 0)에서 (N -1, M -1)까지의 최단 거리
	// 도달 불가능 하면 -1
	static int BFS() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {0, 0, K}); BOOKED[0][0][K] = true;
		DIST[0][0][K] = 0;
		while (!q.isEmpty()) {
			int[] here = q.poll();
			int y = here[0]; int x = here[1]; int k = here[2];
			// 원숭이 처럼 이동
			for (int next = 0; next < 4; next++) {
				int ty = y + dy1[next]; int tx = x + dx1[next];
				if (inRange(ty, tx) && MOVABLE[ty][tx] && !BOOKED[ty][tx][k]) {
					q.offer(new int[] {ty, tx, k});
					BOOKED[ty][tx][k] = true;
					DIST[ty][tx][k] = DIST[y][x][k] + 1;
					if (ty == N - 1 && tx == M - 1) {return DIST[ty][tx][k];}
				}
			}
			// 말 처럼 이동
			if (k > 0) {
				for (int next = 0; next < 8; next++) {
					int ty = y + dy2[next]; int tx = x + dx2[next];
					if (inRange(ty, tx) && MOVABLE[ty][tx] && !BOOKED[ty][tx][k - 1]) {
						q.offer(new int[] {ty, tx, k - 1});
						BOOKED[ty][tx][k - 1] = true;
						DIST[ty][tx][k - 1] = DIST[y][x][k] + 1;
						if (ty == N - 1 && tx == M - 1) {return DIST[ty][tx][k - 1];}
					}
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		MOVABLE = new boolean[N][M];
		BOOKED = new boolean[N][M][K + 1];
		DIST = new int[N][M][K + 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				MOVABLE[i][j] = st.nextToken().equals("0");
			}
		}
		if (N == 1 && M == 1) {System.out.println(0);}
		else {System.out.println(BFS());}
	}

}