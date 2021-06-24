import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N; static int M;
	static boolean[][] MAP;
	static boolean[][] visit;

	// 대각선을 포함한 인접한 구역
	static final int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
	static final int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};

	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	static void DFS(int y, int x) {
		visit[y][x] = true;
		for (int m = 0; m < 8; m++) {
			int ny = y + dy[m]; int nx = x + dx[m];
			if (inRange(ny, nx) && MAP[ny][nx] && !visit[ny][nx]) {
				DFS(ny, nx);
			}
		}
	}

	static int DFSAll() {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (MAP[i][j] && !visit[i][j]) {
					DFS(i, j); sum++;
				}
			}
		}
		return sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		MAP = new boolean[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				MAP[i][j] = st.nextToken().equals("1");
			}
		}
		System.out.println(DFSAll());
	}

}
