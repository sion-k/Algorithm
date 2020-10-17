package baekjoon.p02589;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N; static int M;
	static boolean[][] LAND;

	// 상하좌우 이동
	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};

	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	// 정점 start에서 bfs해서 최대 거리 반환
	static int BFS(int y, int x) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {y, x});
		boolean[][] BOOKED = new boolean[N][M];
		BOOKED[y][x] = true;
		int[][] DIST = new int[N][M];
		DIST[y][x] = 0;

		int max = 0;
		while(!q.isEmpty()) {
			int[] here = q.poll();
			for (int next = 0; next < 4; next++) {
				int ty = here[0] + dy[next]; int tx = here[1] + dx[next];
				if(inRange(ty, tx) && LAND[ty][tx] && !BOOKED[ty][tx]) {
					q.offer(new int[] {ty, tx});
					BOOKED[ty][tx] = true;
					DIST[ty][tx] = DIST[here[0]][here[1]] + 1;
					max = Math.max(max, DIST[ty][tx]);
				}
			}
		}
		return max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		LAND = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String row = br.readLine();
			for (int j = 0; j < M; j++) {
				LAND[i][j] = (row.charAt(j) == 'L');
			}
		}

		int max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (LAND[i][j]) {max = Math.max(max, BFS(i, j));}
			}
		}
		System.out.println(max);
	}

}