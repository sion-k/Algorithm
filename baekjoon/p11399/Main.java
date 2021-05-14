package baekjoon.p11399;

import java.util.*;

public class Main {
	private static int[] p;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int c = sc.nextInt();
		p = new int[c];

		for (int i = 0; i < c; i++)
			p[i] = sc.nextInt();
		Arrays.sort(p);
		int sum = p[0];
		for (int i = 1; i < c; i++) {
			p[i] += p[i - 1];
			sum += p[i];
		}
		System.out.println(sum);
		sc.close();
	}
}
