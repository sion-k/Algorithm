import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N; static int M;
	static int[][] MAP;
	static boolean[][] VISIT;
	static int MAX = 1;

	// {서 북 동 남}의 상대적 위치
	static final int[] dy = {0, -1, 0, 1};
	static final int[] dx = {-1, 0, 1, 0};
	static final int[] wall = { 1, 2, 4, 8 };

	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	// 방의 개수 반환
	static int DFSAll() {
		VISIT = new boolean[N][M];
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!VISIT[i][j]) {
					MAX = Math.max(MAX, DFS(i, j));
					cnt++;
				}
			}
		}
		return cnt;
	}

	// 방의 넓이 반환
	static int DFS(int y, int x) {
		VISIT[y][x] = true;
		int cnt = 0;
		int wall = MAP[y][x];
		for (int next = 0; next < 4; next++) {
			int r = wall % 2; wall = wall / 2;
			if (r == 0) {
				int ty = y + dy[next]; int tx = x + dx[next];
				if (inRange(ty, tx) && !VISIT[ty][tx]) {
					cnt += DFS(ty, tx);
				}
			}

		}
		return 1 + cnt;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		MAP = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				MAP[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(DFSAll());
		System.out.println(MAX);
		MAX = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 서 북 동 남순으로 벽을 부술 수 있으면 하나 씩 부수면서 시도
				for (int b = 0; b < 4; b++) {
					if (MAP[i][j] - wall[b] >= 0) {
						MAP[i][j] -= wall[b];
						DFSAll();
						MAP[i][j] += wall[b];
					}
				}
			}
		}
		System.out.println(MAX);
	}

}
