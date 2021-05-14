package baekjoon.p01018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// true ��� false ������
	static boolean[][] BOARD;

	// ü������ 2���� ��� �ǿ����� ����̰ų� ������
	static int paint(int y, int x) {
		int whiteBoard = 0;
		for (int i = y; i < y + 8; i++) {
			for (int j = x; j < x + 8; j++) {
				if (i % 2 == j % 2) {
					whiteBoard += BOARD[i][j] ? 0 : 1;
				} else {
					whiteBoard += BOARD[i][j] ? 1 : 0;
				}
			}
		}
		int blackBoard = 0;
		for (int i = y; i < y + 8; i++) {
			for (int j = x; j < x + 8; j++) {
				if (i % 2 == j % 2) {
					blackBoard += !BOARD[i][j] ? 0 : 1;
				} else {
					blackBoard += !BOARD[i][j] ? 1 : 0;
				}
			}
		}
		return Math.min(whiteBoard, blackBoard);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		BOARD = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String row = br.readLine();
			for (int j = 0; j < M; j++) {
				BOARD[i][j] = row.charAt(j) == 'W';
			}
		}
		int min = N * M;
		for (int i = 0; i <= N - 8; i++) {
			for (int j = 0; j <= M - 8; j++) {
				min = Math.min(min, paint(i, j));
			}
		}
		System.out.println(min);
	}

}