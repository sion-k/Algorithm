import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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

	// 정점 start에서 bfs해서 최대 거리 반환
	static int BFS(int sy, int sx) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {sy, sx});
		int[][] dist = new int[N][M];
		for (int i = 0; i < N; i++) Arrays.fill(dist[i], -1);
		dist[sy][sx] = 0;

		int max = 0;
		while(!q.isEmpty()) {
			int[] here = q.poll();
			int y = here[0]; int x = here[1];
			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d]; int nx = x + dx[d];
				if (!inRange(ny, nx) || !MAP[ny][nx] || dist[ny][nx] != -1) continue;
				q.offer(new int[] {ny, nx});
				dist[ny][nx] = dist[y][x] + 1;
				max = Math.max(max, dist[ny][nx]);
			}
		}
		return max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		MAP = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String row = br.readLine();
			for (int j = 0; j < M; j++) {
				MAP[i][j] = (row.charAt(j) == 'L');
			}
		}

		int max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (MAP[i][j]) {max = Math.max(max, BFS(i, j));}
			}
		}
		System.out.println(max);
	}

}
