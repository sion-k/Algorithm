package baekjoon.p16234;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N; static int L; static int R;
	static int[][] MAP;
	static int[][] NEWMAP;
	static boolean[][] VISIT;
	static boolean[][] SELECT;

	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};

	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}

	static boolean canOpen(int y1, int x1, int y2, int x2) {
		int diff = Math.abs(MAP[y1][x1] - MAP[y2][x2]);
		return L <= diff && diff <= R;
	}

	// 2개 이상의 나라의 연합이 구성되어야 인구 이동이 일어난다
	// 서로 연결되는 연합들의 합과 그 수를 반환
	static int[] DFS(int y, int x) {
		VISIT[y][x] = true;
		SELECT[y][x] = true;
		int[] ret = new int[2];
		for (int next = 0; next < 4; next++) {
			int ny = y + dy[next]; int nx = x + dx[next];
			if (inRange(ny, nx) && canOpen(y, x, ny, nx) && !VISIT[ny][nx]) {
				int[] temp = DFS(ny, nx);
				ret[0] += temp[0]; ret[1] += temp[1];
			}
		}
		ret[0] += MAP[y][x]; ret[1] += 1;
		return ret;
	}

	// 열 수 있는 모든 국경선을 열고 인구 이동을 시도 일어나는지 여부
	static boolean DFSAll() {
		boolean moved = false;
		VISIT = new boolean[N][N];
		SELECT = new boolean[N][N];
		NEWMAP = MAP.clone();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!VISIT[i][j]) {
					int[] pair = DFS(i, j);
					int mean = pair[0] / pair[1];
					selectDFS(i, j, mean);
					if (pair[1] >= 2) {moved = true;}
				}
			}
		}
		MAP = NEWMAP;
		return moved;
	}

	static void selectDFS(int y, int x, int mean) {
		SELECT[y][x] = false;
		NEWMAP[y][x] = mean;
		for (int next = 0; next < 4; next++) {
			int ny = y + dy[next]; int nx = x + dx[next];
			if (inRange(ny, nx) && SELECT[ny][nx]) {
				selectDFS(ny, nx, mean);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		MAP = new int[N][N]; NEWMAP = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				MAP[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int cnt = 0;
		while (DFSAll()){cnt++;}
		System.out.println(cnt);
	}

}