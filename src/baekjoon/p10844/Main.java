package baekjoon.p10844;

import java.util.Scanner;

public class Main {
	static int[] cache;
	
	static int stair(int N) {
		if (N == 1) {return 9;}
		if (cache[N] != 0) {return cache[N];}
		return cache[N] = (2 * stair(N - 1) - (N - 1)) % 1_000_000_000 ;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.close();
		cache = new int[N + 1];
		System.out.println(stair(N));
	}

}