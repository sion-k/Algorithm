package baekjoon.p02448;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	static char[][] BOARD;
	static void stamp(int N, int y, int x) {
		if (N == 3) {
			BOARD[y][x] = BOARD[y+1][x-1] = BOARD[y+1][x+1] =
            BOARD[y+2][x-2] = BOARD[y+2][x-1] = BOARD[y+2][x] =
            BOARD[y+2][x+1] = BOARD[y+2][x+2] = '*';
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