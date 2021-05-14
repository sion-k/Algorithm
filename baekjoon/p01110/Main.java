package baekjoon.p01110;

import java.util.Scanner;

public class Main {
	private static int addCycle(int i) {
		if (i < 10) {
			return (i % 10) * 10 + (i) % 10;
		} else {
			return (i % 10) * 10 + (i / 10 + i % 10) % 10;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.close();
		int sum = 1;
		int next = N;
		while (N != (next = addCycle(next))) {
			sum++;
		}
		System.out.println(sum);
	}

}
