package baekjoon.p01261;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N; static int M;
	static int[][] MAP;

	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};

	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	static final int INF = 10001;

	static int[][] Dijkstra(int sy, int sx) {
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.offer(new Pair(0, 0, 0));
		int[][] dist = new int[N][M];
		for (int i = 0; i < N; i++) {Arrays.fill(dist[i], INF);}
		dist[sy][sx] = 0;
		while (!pq.isEmpty()) {
			Pair here = pq.poll();
			int y = here.y ; int x = here.x; int cost = here.cost;
			if (dist[y][x] < cost) {continue;}
			for (int m = 0; m < 4; m++) {
				int ny = y + dy[m]; int nx = x + dx[m];
				if (inRange(ny, nx)) {
					int nextDist = cost + MAP[ny][nx];
					if (nextDist < dist[ny][nx]) {
						pq.offer(new Pair(ny, nx, nextDist));
						dist[ny][nx] = nextDist;
					}
					if (ny == N - 1 && nx == M - 1) {return dist;}
				}
			}
		}
		return dist;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		MAP = new int[N][M];
		for (int i = 0; i < N; i++) {
			String row = br.readLine();
			for (int j = 0; j < M; j++) {
				MAP[i][j] = (row.charAt(j) -'0');
			}
		}
		if (N == 1 && M == 1) {System.out.println(0);}
		else {System.out.println(Dijkstra(0, 0)[N - 1][M - 1]);}
	}

}

class Pair implements Comparable<Pair> {
	int y; int x; int cost;
	public Pair(int y, int x, int c) {this.y = y; this.x = x; this.cost = c;}
	@Override
	public int compareTo(Pair o) {return cost - o.cost;}
}