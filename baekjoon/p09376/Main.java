package baekjoon.p09376;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static char[][] S;
	
	static final int[] dy = { -1, 1, 0, 0 };
	static final int[] dx = { 0, 0, -1, 1 };
	
	static final int INF = 10000;
	
	static boolean inRange(int y, int x) { return 0 <= y && y < N && 0 <= x && x < M; }
	
	static int[][] dijkstra(int[] s) {
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.offer(new Pair(s[0], s[1], 0));
		int[][] dist = new int[N][M];
		for (int i = 0; i < N; i++) Arrays.fill(dist[i], INF);
		dist[s[0]][s[1]] = 0;
		while (!pq.isEmpty()) {
			Pair p = pq.poll();
			int y = p.y; int x = p.x; int d = p.d;
			if (dist[y][x] < d) continue;
			for (int n = 0; n < 4; n++) {
				int ny = y + dy[n]; int nx = x + dx[n];
				if (!inRange(ny, nx) || S[ny][nx] == '*') continue;
				int nd = d + (S[ny][nx] == '#' ? 1 : 0);
				if (dist[ny][nx] > nd) {
					dist[ny][nx] = nd;
					pq.offer(new Pair(ny, nx, nd));
				}
			}
		}
		return dist;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()) + 2;
			M = Integer.parseInt(st.nextToken()) + 2;
			S = new char[N][M];
			for (int i = 0; i < N; i++) Arrays.fill(S[i], '.');
			int[] A = null; int[] B = null;
			for (int i = 1; i <= N - 2; i++) {
				String row = br.readLine();
				for (int j = 1; j <= M - 2; j++) {
					S[i][j] = row.charAt(j - 1);
					if (S[i][j] == '$') {
						if (A == null) A = new int[] { i, j };
						else B = new int[] { i, j };
					}
				}
			}
			int[][][] dist = new int[3][N][M];
			dist[0] = dijkstra(A);
			dist[1] = dijkstra(B);
			dist[2] = dijkstra(new int[] { 0, 0 });
			int min = 30000;
			for (int i = 0; i < N; i++)
				for (int j = 0; j < M; j++)
					min = Math.min(min, dist[0][i][j] + dist[1][i][j] + dist[2][i][j] - (S[i][j] == '#' ? 2 : 0));
			bw.append(min).append("\n");
		}
		System.out.print(bw);
	}
	
}

class Pair implements Comparable<Pair> {
	int y, x, d;
	
	Pair(int y, int x, int d) { this.y = y; this.x = x; this.d = d; }
	
	@Override
	public int compareTo(Pair o) { return d - o.d; }
	
}
