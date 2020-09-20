package baekjoon.p17175;

import java.util.*;

public class Main {
	private static int[] cache = new int[50 + 1];

	private static int fiboCount(int n) {
		if (n < 2) {
			return 1;
		}
		if (cache[n] != 0) {
			return cache[n];
		}
		return cache[n] = (fiboCount(n - 2) + fiboCount(n - 1) + 1) % 1000000007;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println(fiboCount(sc.nextInt()));
		sc.close();
	}

}
