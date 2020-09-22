package baekjoon.p16503;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int cal(int a, int b, char op) {
		switch (op) {
		case '*':
			return a * b;
		case '/':
			if (a < 0) {
				a *= -1;
				return -1 * (a / b);
			}
			if (b < 0) {
				b *= -1;
				return -1 * (a / b);
			}
			return a / b;
		case '+':
			return a + b;
		case '-':
			return a - b;
		}
		return 0;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String exp = sc.nextLine();
		sc.close();
		
		String[] term = exp.split(" ");
		int[] k = new int[3];
		k[0] = Integer.parseInt(term[0]);
		k[1] = Integer.parseInt(term[2]);
		k[2] = Integer.parseInt(term[4]);

		char[] op = new char[2];
		op[0] = term[1].charAt(0);
		op[1] = term[3].charAt(0);
		
		int[] val = new int[2];
		val[0] = cal(cal(k[0], k[1], op[0]), k[2], op[1]);
		val[1] = cal(k[0], cal(k[1], k[2], op[1]), op[0]);
		
		Arrays.sort(val);
		
		System.out.println(val[0]);
		System.out.println(val[1]);

		
	}

}
