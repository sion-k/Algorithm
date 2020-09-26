package baekjoon.p2810;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		String S = sc.nextLine();
		sc.close();
		// 전에 N명중에서 커플석 개수만큼 못 쓰는 사람이 생긴다.
		int couple = 0;
		for (int i = 1; i < N; i++) {
			if (S.charAt(i) == 'L' && S.charAt(i - 1) == 'L') {
				couple++;
				i++;
			}
		}
		// 하지만 맨 오른쪽에 있는 커플석은 둘 다 쓸 수 있다.
		if (S.charAt(S.length() - 1) == 'L' && S.charAt(S.length() - 2) == 'L') {
			couple--;
		}
		
		System.out.println(N - couple);
	}

}