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
		System.out.println(chair + 1);
	}

}