package baekjoon.p1065;

import java.util.Scanner;

public class Main {
	static boolean func(String n) {
		if (n.length() <= 2) {return true;}

		int[] digit = new int[n.length()];
		for (int i = 0; i < digit.length; i++) {
			digit[i] = n.charAt(i) - '0';
		}

		int diff = digit[1] - digit[0];
		for (int i = 1; i < digit.length - 1; i++) {
			if ((digit[i + 1] - digit[i]) != diff) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.close();
		int sum = 0;
		for (int i = 1; i <= N; i++) {
			if (func(i + "")) {sum++;}
		}
		System.out.println(sum);
	}

}
