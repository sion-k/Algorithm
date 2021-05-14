package baekjoon.p14927;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N; static boolean[][] MAP;

	static final int[] dy = {-1, 0, 0, 0, 1};
	static final int[] dx = {0, -1, 0, 1, 0};

	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}

	static void push(int y, int x) {
		for (int m = 0; m < 5; m++) {
			int ny = y + dy[m]; int nx = x + dx[m];
			if (!inRange(ny, nx)) {continue;}
			MAP[ny][nx] = !MAP[ny][nx];
		}
	}

	static int min;

	static void BFC(int j, int pushed) {
		if (j == N) {
			min = Math.min(min, getMinPush() + pushed);
			return;
		}
		BFC(j + 1, pushed); push(0, j);
		BFC(j + 1, pushed + 1); push(0, j);
	}

	static int getMinPush() {
		boolean[][] temp = new boolean[N][N];
		for (int i = 0; i < N; i++) {temp[i] = MAP[i].clone();}
		int cnt = 0;
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (MAP[i - 1][j]) {push(i, j); cnt++;}
			}
		}
		boolean allOff = true;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (MAP[i][j]) {allOff = false; break;}
			}
			if (!allOff) {break;}
		}
		if (!allOff) {cnt = N * N + 1;}
		MAP = temp;
		return cnt;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); MAP = new boolean[N][N];
		min = N * N + 1;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				MAP[i][j] = st.nextToken().equals("1");
			}
		}
		BFC(0, 0);
		if (min == (N * N + 1)) {System.out.println(-1);}
		else {System.out.println(min);}
	}

}