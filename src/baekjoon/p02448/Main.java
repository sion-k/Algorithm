package baekjoon.p02448;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	static char[][] BOARD;
	static final int[] dy = {0, 1, 1, 2, 2, 2, 2, 2};
	static final int[] dx=  {0, -1, 1, -2, -1, 0, 1, 2};

	static void stamp(int N, int y, int x) {
		if (N == 3) {
			for (int m = 0; m < 8; m++) {
				int ny = y + dy[m]; int nx = x + dx[m];
				BOARD[ny][nx] = '*';
			}
			return;
		}
		stamp(N / 2, y, x);
		stamp(N / 2, y + N / 2, x + N / 2);
		stamp(N / 2, y + N / 2, x - N / 2);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		BOARD = new char[N][ 2 * N - 1];
		for (int i = 0; i < N; i++) {Arrays.fill(BOARD[i], ' ');}
		stamp(N, 0, N - 1);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 2 * N - 1; j++) {bw.write(BOARD[i][j]);}
			bw.newLine();
		}
		bw.close();
	}

}