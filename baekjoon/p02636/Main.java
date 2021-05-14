package baekjoon.p02636;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N; static int M;
	static boolean[][] MAP; //ġ� �ִ��� ����
	static boolean[][] NEXTMAP;
	static boolean[][] VISIT;
	static boolean[][] MELTVISIT;

	static int PREV;

	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};

	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	// ġ� ��� ��µ� �ɸ��� �ð� ��ȯ
	static int MELT() {
		int t = 0; int cheese = 0;
		NEXTMAP = MAP.clone();
		while((cheese = DFSAll()) != 0) {
			PREV = cheese;
			MELTVISIT = new boolean[N][M];
			MELTDFS(0, 0);
			MAP = NEXTMAP;
			t++;
		}
		return t;
	}

	// ġ���� ���� ���� ��ȯ
	static int DFSAll() {
		VISIT = new boolean[N][M];
		int sum = 0;
		for (int i = 1; i < N - 1; i++) {
			for (int j = 1; j < M - 1; j++) {
				if (MAP[i][j] && !VISIT[i][j]) {
					sum += DFS(i, j);
				}
			}
		}
		return sum;
	}

	// �湮�� ġ�� ���� ��ȯ
	static int DFS(int y, int x) {
		VISIT[y][x] = true;
		int sum = 1;
		for (int m = 0; m < 4; m++) {
			int ny = y + dy[m]; int nx = x + dx[m];
			if (MAP[ny][nx] && !VISIT[ny][nx]) {
				sum += DFS(ny, nx);
			}
		}
		return sum;
	}

	// ġ� ������ ������
	static void MELTDFS(int y, int x) {
		MELTVISIT[y][x] = true;
		for (int m = 0; m < 4; m++) {
			int ny = y + dy[m]; int nx = x + dx[m];
			if (inRange(ny, nx) && !MELTVISIT[ny][nx]) {
				if (MAP[ny][nx]) {
					MELTVISIT[ny][nx] = true;
					NEXTMAP[ny][nx] = false;
				}
				else {MELTDFS(ny, nx);}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		MAP = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				MAP[i][j] = st.nextToken().equals("1");
			}
		}
		System.out.println(MELT());
		System.out.println(PREV);
	}

}