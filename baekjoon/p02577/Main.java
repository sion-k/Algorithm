package baekjoon.p02577;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String product = (sc.nextInt() * sc.nextInt() * sc.nextInt()) + "";
		sc.close();
		int[] decimal = new int[10];
		for (int i = 0; i < product.length(); i++) {
			decimal[product.charAt(i) - '0']++;
		}
		for (int i = 0; i < decimal.length; i++) {
			System.out.println(decimal[i]);
		}
		
	}

}