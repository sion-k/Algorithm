package baekjoon.p17212;

import java.util.*;

public class Main {
	private static int minCoin(int price) {
		int p = price / 7;
		int q = price % 7;
		switch (q) {
		case 0:
			return p;
		case 1:
		case 2:
		case 5:
			return p + 1;
		case 3:
			return p > 0 ? p + 1 : p + 2;
		default:
			return p + 2;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int price = sc.nextInt();
		System.out.println(minCoin(price));
		sc.close();
	}
}
