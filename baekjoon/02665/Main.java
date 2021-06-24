import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
	static int N;
	static boolean[][] MAP;

	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};

	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}

	static int[][] Dijkstra() {
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.offer(new Pair(0, 0, 0));
		int[][] dist = new int[N][N];
		for (int i = 0; i < N; i++) {Arrays.fill(dist[i], 2500);}
		dist[0][0] = 0;

		while (!pq.isEmpty()) {
			Pair here = pq.poll();
			int y = here.y; int x = here.x; int cost = here.cost;
			if (dist[y][x] < cost) {continue;}
			for (int m = 0; m < 4; m++) {
				int ny = y + dy[m]; int nx = x + dx[m];
				if (inRange(ny, nx)) {
					 int nextCost = cost + (MAP[ny][nx] ? 0 : 1);
					 if (dist[ny][nx] > nextCost) {
						 dist[ny][nx] = nextCost;
						 pq.offer(new Pair(ny, nx, nextCost));
					 }
				}
			}
		}
		return dist;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		MAP = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String row = br.readLine();
			for (int j = 0; j < N; j++) {
				MAP[i][j] = (row.charAt(j) == '1');
			}
		}
		System.out.println(Dijkstra()[N - 1][N - 1]);
	}

}

class Pair implements Comparable<Pair> {
	int y; int x; int cost;
	public Pair(int y, int x, int c) {this.y = y; this.x = x; cost = c;}
	@Override
	public int compareTo(Pair o) {return cost - o.cost;}
}
