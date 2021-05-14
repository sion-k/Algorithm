package baekjoon.p01783;

import java.util.*;

public class Main {
	private static int travel(int N, int M) {
		if (N >= 3 && M >= 7) {
			return M - 2;
		} else {
			if (N == 1 || M == 1) {
				return 1;
			}
			if (N == 2) {
				if (M >= 7) {
					return 4;
				} else {
					return M / 2 + M % 2;
				}
			}
			if (M >= 4) {
				return 4;
			} else {
				return M;
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		System.out.println(travel(N, M));
		sc.close();
	}
}
