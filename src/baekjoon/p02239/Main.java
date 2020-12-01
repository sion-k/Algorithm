package baekjoon.p02239;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[][] SUDOKU;

	static boolean[][] rowTaken = new boolean[9][10];
	static boolean[][] colTaken = new boolean[9][10];
	static boolean[][][] cellTaken = new boolean[3][3][10];

	static StringBuilder ans = new StringBuilder();
	static boolean found = false;

	static void print() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++)
				ans.append(SUDOKU[i][j]);
			ans.append('\n');
		}
		System.out.print(ans);
	}

	static void BTK(int n) {
		if (found) {return;}
		if (n >= 81) {found = true; print(); return;}
		int i = n / 9; int j = n % 9;
		if (SUDOKU[i][j] != 0) {BTK(n + 1); return;}
		for (int k = 1; k <= 9; k++) {
			if (!rowTaken[i][k] && !colTaken[j][k] && !cellTaken[i / 3][j / 3][k]) {
				rowTaken[i][k] = true;
				colTaken[j][k] = true;
				cellTaken[i / 3][j / 3][k] = true;
				SUDOKU[i][j] = k;
				BTK(n + 1);
				rowTaken[i][k] = false;
				colTaken[j][k] = false;
				cellTaken[i / 3][j / 3][k] = false;
				SUDOKU[i][j] = 0;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		SUDOKU = new int[9][9];
		for (int i = 0; i < 9; i++) {
			String row = br.readLine();
			for (int j = 0; j < 9; j++) {
				int k = SUDOKU[i][j] = row.charAt(j) - '0';
				rowTaken[i][k] = true;
				colTaken[j][k] = true;
				cellTaken[i / 3][j / 3][k] = true;
			}
		}
		BTK(0);
	}

}