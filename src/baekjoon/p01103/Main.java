package baekjoon.p01103;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N; static int M; static char[][] MAP;
	static boolean[][] visit; static boolean[][] check;
	static int[][] cache;

	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};

	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	// (y, x)���� �����ϴ� ��ο� ����Ŭ�� �����ϴ��� ��ȯ
	static boolean dfs(int y, int x) {
		// ���� ����Ŭ ���θ� Ȯ���ϱ� ���� check��� ����Ŭ
		if (check[y][x]) {return true;}
		// �װ� �ƴ϶�� �湮�� ������ �н�
		if (visit[y][x]) {return false;}
		visit[y][x] = check[y][x] = true;
		for (int m = 0; m < 4; m++) {
			int d = MAP[y][x] - '0';
			int ny = y + d * dy[m]; int nx = x + d * dx[m];
			if (!inRange(ny, nx) || MAP[ny][nx] == 'H') continue;
			if (dfs(ny, nx)) return true;
		}
		check[y][x] = false;
		return false;
	}

	// (y, x)���� �ִ� ������ ������ �� �ִ� Ƚ��
	static int dp(int y, int x) {
		if (cache[y][x] != 0) {return cache[y][x];}
		// (ny, nx)�� �����̴���, �ٱ��̴��� 1���� ������ �� �ִ�
		int max = 1;
		for (int m = 0; m < 4; m++) {
			int d = MAP[y][x] - '0';
			int ny = y + d * dy[m]; int nx = x + d * dx[m];
			if (!inRange(ny, nx) || MAP[ny][nx] == 'H') continue;
			max = Math.max(max, 1 + dp(ny, nx));
		}
		return cache[y][x] = max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		MAP = new char[N][M];
		for (int i = 0; i < N; i++) {
			String row = br.readLine();
			for (int j = 0; j < M; j++)
				MAP[i][j] = row.charAt(j);
		}
		visit = new boolean[N][M]; check = new boolean[N][M];
		if (dfs(0, 0)) {
			System.out.println(-1);
		} else {
			cache = new int[N][M];
			System.out.println(dp(0, 0));
		}
	}

}