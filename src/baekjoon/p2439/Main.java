package baekjoon.p2439;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.close();
		// �� �ٸ��� 1���� N�� ������ ���� ������
		for (int i = 1; i <= N; i++) {
			// ������ �ﰢ���� ���� �� �ٴ� ũ��(N)���� �̹� �ٿ� ���� �� ����(i)�� �� ��
			for (int j = 0; j < N - i; j++) {
				System.out.print(" ");
			}
			// i��° �࿡�� i���� ���� ���´�
			for (int j = 0; j < i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

}