package baekjoon.p14939;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static boolean[][] MAP = new boolean[10][10];

	static final int[] dy = {-1, 0, 0, 0, 1};
	static final int[] dx = {0, -1, 0, 1, 0};

	static boolean inRange(int y, int x) {
		return 0 <= y && y < 10 && 0 <= x && x < 10;
	}

	static void push(int y, int x) {
		for (int m = 0; m < 5; m++) {
			int ny = y + dy[m]; int nx = x + dx[m];
			if (!inRange(ny, nx)) {continue;}
			MAP[ny][nx] = !MAP[ny][nx];
		}
	}

	static int min = 101;

	static void BFC(int j, int pushed) {
		if (j == 10) {
			min = Math.min(min, getMinPush() + pushed);
			return;
		}
		BFC(j + 1, pushed);
		push(0, j);
		BFC(j + 1, pushed + 1);
		push(0, j);
	}

	static int getMinPush() {
		boolean[][] temp = new boolean[10][10];
		for (int i = 0; i < 10; i++) {temp[i] = MAP[i].clone();}
		int cnt = 0;
		for (int i = 1; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (MAP[i - 1][j]) {push(i, j); cnt++;}
			}
		}
		boolean allOff = true;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (MAP[i][j]) {allOff = false; break;}
			}
			if (!allOff) {break;}
		}
		if (!allOff) {cnt = 101;}
		MAP = temp;
		return cnt;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 10; i++) {
			String row = br.readLine();
			for (int j = 0; j < 10; j++) {
				MAP[i][j] = (row.charAt(j) == 'O');
			}
		}
		BFC(0, 0);
		if (min == 101) {System.out.println(-1);}
		else {System.out.println(min);}
	}

}