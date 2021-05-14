package baekjoon.p10844;

import java.util.Scanner;

public class Main {
	static int[][] cache;
	static int N;
	
	// [1, N]��° ���ڸ� d�� ����� �� �ٿ��� ��� �� ����� ��
	static int stair(int i, int d) {
		// ���� ��� : ������ ���ڴ� �� ���� �Ѱ���
		if (i == N) {return 1;}
		if (cache[i][d] != 0) {return cache[i][d];}
		if (d == 0) {return cache[i][d] = stair(i + 1, 1);}
		if (d == 9) {return cache[i][d] = stair(i + 1, 8);}
		return cache[i][d] = (stair(i + 1, d - 1) + stair(i + 1, d + 1)) % 1000000000;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		sc.close();
		cache = new int[N + 1][9 + 1];
		
		int stair = 0;
		for (int i = 1; i <= 9; i++) {
			stair = ((stair + stair(1, i)) % 1000000000);
		}
		
		System.out.println(stair);
	}

}