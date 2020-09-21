package baekjoon.p1978;

import java.util.Scanner;

public class Main {
	private static boolean isPrime(int N) {
		if (N == 1 )return false;
		for (int d = 2; d < N; d++) {
			if (N % d == 0)
				return false;
		}
		return true;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int prime = 0;
		for (int i = 0; i < N; i++) {
			if (isPrime(sc.nextInt())) {prime++;}
		}
		sc.close();
		System.out.println(prime);
	}

}
