package baekjoon.p2810;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		String S = sc.nextLine();
		sc.close();
		// ���� N���߿��� Ŀ�ü� ������ŭ �� ���� ����� �����.
		int couple = 0;
		for (int i = 1; i < N; i++) {
			if (S.charAt(i) == 'L' && S.charAt(i - 1) == 'L') {
				couple++;
				i++;
			}
		}
		// ������ �� �����ʿ� �ִ� Ŀ�ü��� �� �� �� �� �ִ�.
		if (S.charAt(S.length() - 1) == 'L' && S.charAt(S.length() - 2) == 'L') {
			couple--;
		}
		
		System.out.println(N - couple);
	}

}