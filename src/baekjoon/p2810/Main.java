package baekjoon.p2810;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		String S = sc.nextLine();
		sc.close();
		int chair = 0;
		for (int i = 0; i < N; i++) {
			if(S.charAt(i) == 'L' && S.charAt(i + 1) == 'L') {
				i++;
			}
			chair++;
		}
		// 컵홀더 수보다 사람이 많다면 컵홀더 수 만큼
		if(N > chair + 1) {
			System.out.println(chair + 1);
		} else {// 컵 홀더 수보다 사람이 적으면 사람 만큼 쓸 수 있음
			System.out.println(N);
		}
	}

}