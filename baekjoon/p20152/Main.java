package baekjoon.p20152;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};

	static boolean inRange(int y, int x) {
		return 0 <= y && y < 31 && 0 <= x && x < 31;
	}

	static long BFS(int H, int N) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {H, H});
		boolean[][] booked = new boolean[31][31];
		booked[H][H] = true;
		int[][] dist = new int[31][31];
		dist[H][H] = 0;
		long[][] moveWay = new long[31][31];
		moveWay[H][H] = 1;

		while (!q.isEmpty()) {
			int[] here = q.poll();
			int y = here[0]; int x = here[1];
			for (int m = 0; m < 4; m++) {
				int ny = y + dy[m]; int nx = x + dx[m];
				if (!inRange(ny, nx) || ny > nx) {continue;}
				if (!booked[ny][nx]) {
					q.offer(new int[] {ny ,nx});
					booked[ny][nx] = true;
					dist[ny][nx] = dist[y][x] + 1;
					moveWay[ny][nx] = moveWay[y][x];
				} else if (dist[ny][nx] == dist[y][x] + 1){
					moveWay[ny][nx] += moveWay[y][x];
				}
			}
		}
		return moveWay[N][N];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int H = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		if (H == N) {System.out.println(1);}
		else {
			System.out.println(BFS(H, N));
		}
	}

}