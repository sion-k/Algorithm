package baekjoon.p01285;

import java.util.*;

public class Main {
	// H T
	private static char[][] coin;
	private static int size;
	private static int minT = 20 * 20;

	private static void bfCount(int i) {
		if (i == size - 1) {
			int notPushed = gdCount();
			pushRow(i);
			int pushed = gdCount();
			pushRow(i);
			minT = Math.min(minT, notPushed < pushed ? notPushed : pushed);
		} else {
			bfCount(i + 1);
			pushRow(i);
			bfCount(i + 1);
			pushRow(i);
		}
	}

	private static int gdCount() {
		int totalT = 0;
		int colT = 0;
		for (int j = 0; j < size; j++) {
			if ((colT = countColT(j)) > size / 2) {
				totalT += (size - colT);
			} else {
				totalT += colT;
			}
		}
		return totalT;
	}

	private static void pushRow(int row) {
		for (int n = 0; n < size; n++) {
			toggle(row, n);
		}
	}

	private static int countColT(int col) {
		int count = 0;
		for (int i = 0; i < size; i++) {
			if (coin[i][col] == 'T') {
				count++;
			}
		}
		return count;
	}

	private static void toggle(int i, int j) {
		coin[i][j] = (coin[i][j] == 'H') ? 'T' : 'H';
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		size = Integer.parseInt(sc.nextLine());
		coin = new char[size][size];

		for (int i = 0; i < size; i++) {
			StringBuilder sb = new StringBuilder(sc.nextLine());
			for (int j = 0; j < size; j++) {
				coin[i][j] = sb.charAt(j);
			}
		}
		sc.close();
		bfCount(0);
		System.out.println(minT);
	}

}
