package baekjoon.p9663;

import java.util.Scanner;

public class Main {
	static int N;
	// ���� ������ �ִ� �� ���� 0�̸� ���� �� �ְ� 1�̻� �̸� ���� �� ����
	static int[][] BOARD;
	
	// ���� ������ �������� ���� �̵��� 0�� ����� �̵��̶� ������ 8���� �̵� ����� �ݽð� �������� ����
	static final int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static final int[] dx = { 1, 1, 0, -1, -1, -1, 0, 1 };
	
	static boolean isRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}
	
	static void setQueen(int y, int x, int delta) {
		BOARD[y][x] += delta;
		int ty = y; int tx = x;
		for(int move = 0; move < 8; move++) {
			while(isRange(ty += dy[move], tx += dx[move])) {
				BOARD[ty][tx] += delta;
			}
			ty = y; tx = x;
		}
	}
	
	// ������ py, px�� ������ �� n���� ���� ���� �� �ִ� ����� ��
	static long set(int py, int px, int n) {
		if (n == 0) {return 1;}
		boolean passed = false;
		if (py == -1 && px == -1) {
			passed = true;
		}
		
		long sum = 0;
		for (int i = py; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(i == py && j == px) {
					passed = true; continue;
				}
				if(passed && BOARD[i][j] == 0) {
					setQueen(i, j, 1);
					sum += set(i, j, n - 1);
					setQueen(i, j, -1);
				}
			}
		}
		return sum;
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		BOARD = new int[N][N];
		long start = System.currentTimeMillis();
		System.out.println(set(-1, -1, N));
		long end = System.currentTimeMillis();
		System.out.println((end - start) / 1000.);
		sc.close();
	}

}