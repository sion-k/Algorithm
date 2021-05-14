package baekjoon.p02193;

import java.util.Scanner;

public class Main {
	static long[] cache;
	
	static long pinary(int N) {
		if (N <= 2) {return 1;}
		if (cache[N] != 0) { return cache[N];}
		return cache[N] = pinary(N - 1) + pinary(N - 2);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.close();
		cache = new long[N + 1];
		System.out.println(pinary(N));
	}

}
