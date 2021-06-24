import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N; static int M;
	static boolean[][] MAP;
	static boolean[][] VISIT;

	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};

	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	static int DFS(int y, int x) {
		VISIT[y][x] = true;
		int sum = 0;
		for (int m = 0; m < 4; m++) {
			int ny = y + dy[m]; int nx = x + dx[m];
			if (inRange(ny, nx) && MAP[ny][nx] && !VISIT[ny][nx]) {
				sum += DFS(ny, nx);
			}
		}
		return 1 + sum;
	}

	static int DFSAll() {
		int max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(MAP[i][j] && !VISIT[i][j]) {
					max = Math.max(max, DFS(i, j));
				}
			}
		}
		return max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()) + 1;
		M = Integer.parseInt(st.nextToken()) + 1;
		MAP = new boolean[N][M];
		VISIT = new boolean[N][M];
		int K = Integer.parseInt(st.nextToken());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			MAP[y][x] = true;
		}
		System.out.println(DFSAll());
	}

}
