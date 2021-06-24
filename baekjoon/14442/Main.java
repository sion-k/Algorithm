import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N; static int M;
	static boolean[][] MAP;

	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};

	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	static int BFS(int sk) {
		if (N == 1 && M == 1) {return 1;}
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {0, 0, sk});
		boolean[][][] booked = new boolean[N][M][sk + 1];
		booked[0][0][sk] = true;
		int[][][] dist = new int[N][M][sk + 1];
		dist[0][0][sk] = 1;

		while (!q.isEmpty()) {
			int[] here = q.poll();
			int y = here[0]; int x = here[1]; int k = here[2];
			for (int m = 0; m < 4; m++) {
				int ny = y + dy[m]; int nx = x + dx[m];
				if (inRange(ny, nx) && !booked[ny][nx][k]) {
					booked[ny][nx][k] = true;
					dist[ny][nx][k] = dist[y][x][k] + 1;
					if (MAP[ny][nx]) {
						q.offer(new int[] {ny, nx, k});
						if (ny == N - 1 && nx == M - 1) {
							return dist[ny][nx][k];
						}
					} else if (k > 0) {
						q.offer(new int[] {ny, nx, k - 1});
						booked[ny][nx][k - 1] = true;
						dist[ny][nx][k - 1] = dist[y][x][k] + 1;
						if (ny == N - 1 && nx == M - 1) {
							return dist[ny][nx][k - 1];
						}
					}
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		MAP = new boolean[N][M];
		int K = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			String row = br.readLine();
			for (int j = 0; j < M; j++) {
				MAP[i][j] = (row.charAt(j) == '0');
			}
		}
		System.out.println(BFS(K));
	}

}
