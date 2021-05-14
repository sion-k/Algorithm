package baekjoon.p11051;

import java.util.Scanner;

public class Main {
	static int cache[][];

	static int comb(int N, int K) {
		if (K == 0 || N == K) {return 1;}
		if (cache[N][K] != 0) {return cache[N][K];}
		return cache[N][K] = (comb(N - 1, K - 1) + comb(N - 1, K)) % 10007;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		sc.close();
		cache = new int[N + 1][K + 1];
		System.out.println(comb(N, K));
	}

}