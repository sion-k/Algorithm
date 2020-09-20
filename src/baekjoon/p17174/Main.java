package baekjoon.p17174;

import java.util.*;

public class Main {

	private static int bundle(int N, int M) {
		int p = N / M;
		int q = N % M;
		if (p == 0) {
			return q;
		}
		return p * M + q + bundle(p, M);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		System.out.println(bundle(N, M));
		sc.close();
	}

}
