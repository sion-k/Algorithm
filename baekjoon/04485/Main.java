import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] MAP;

	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};

	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}

	static int[][] Dijkstra(){
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.offer(new Pair(0, 0, MAP[0][0]));
		int[][] dist = new int[N][N];
		for (int i = 0; i < N; i++) {Arrays.fill(dist[i], 1000000);}
		dist[0][0] = MAP[0][0];

		while (!pq.isEmpty()) {
			Pair here = pq.poll();
			int y = here.y; int x = here.x; int cost = here.cost;
			if (dist[y][x] < cost) {continue;}
			for (int m = 0; m < 4; m++) {
				int ty = y + dy[m]; int tx = x + dx[m];
				if (inRange(ty, tx)) {
					int nextDist = cost + MAP[ty][tx];
					if (dist[ty][tx] > nextDist) {
						dist[ty][tx] = nextDist;
						pq.offer(new Pair(ty, tx, nextDist));
					}
				}
			}
		}
		return dist;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int t = 1;
		while (N != 0) {
			MAP = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					MAP[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			System.out.println("Problem " + t + ": " + Dijkstra()[N - 1][N - 1]);
			N = Integer.parseInt(br.readLine()); t++;
		}
	}

}

class Pair implements Comparable<Pair> {
	int y; int x; int cost;
	public Pair(int y, int x, int c) {this.y = y; this.x = x; cost = c;}
	@Override
	public int compareTo(Pair o) {return cost - o.cost;}
}
