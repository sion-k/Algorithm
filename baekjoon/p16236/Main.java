package baekjoon.p16236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] MAP;
	static PriorityQueue<Pos> CAND = new PriorityQueue<>();

	// 상하좌우 이동
	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};

	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}

	static int BFS(int sy, int sx) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {sy, sx});
		boolean[][] BOOKED = new boolean[N][N];
		BOOKED[sy][sx] = true;
		int[][] DIST = new int[N][N];
		DIST[sy][sx] = 0;
		int minPath = 400;
		int size = 2; int eaten = 0;
		int moved = 0;

		while (!q.isEmpty()) {
			int[] here = q.poll();
			int y = here[0]; int x = here[1];
			for (int next = 0; next < 4; next++) {
				int ny = y + dy[next]; int nx = x + dx[next];
				if (inRange(ny, nx) && size >= MAP[ny][nx] && !BOOKED[ny][nx]) {
					BOOKED[ny][nx] = true;
					DIST[ny][nx] = DIST[y][x] + 1;
					if ((minPath == 0 || DIST[ny][nx] <= minPath) && MAP[ny][nx] != 0 && size > MAP[ny][nx]) {
						minPath = DIST[ny][nx];
						CAND.offer(new Pos(ny, nx));
					}
					if (DIST[ny][nx] + 1 <= minPath) {
						q.offer(new int[] {ny, nx});
					}
				}
			}
			if (q.isEmpty() && !CAND.isEmpty()) {
				Pos next = CAND.poll();
				int ny = next.y; int nx = next.x;
				MAP[ny][nx] = 0;
				q.clear(); q.offer(new int[] {ny, nx});
				BOOKED = new boolean[N][N];
				BOOKED[ny][nx] = true;
				moved += DIST[ny][nx];
				DIST = new int[N][N];
				DIST[ny][nx] = 0;
				minPath = 400;
				if (++eaten >= size) {size++; eaten = 0;}
				CAND.clear();
			}
		}
		return moved;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		MAP = new int[N][N];
		int y = 0; int x = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				MAP[i][j] = Integer.parseInt(st.nextToken());
				if (MAP[i][j] == 9) {y = i; x = j; MAP[i][j] = 0;}
			}
		}
		System.out.println(BFS(y, x));
	}
}

class Pos implements Comparable<Pos> {
	int y; int x;
	public Pos(int y, int x) {this.y = y; this.x = x;}
	@Override
	public int compareTo(Pos o) {return y == o.y ? x - o.x : y - o.y;}
}