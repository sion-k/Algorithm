package baekjoon.p02869;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int A = sc.nextInt();
		int B = sc.nextInt();
		int V = sc.nextInt();
		sc.close();
		int days = (V - A) / (A - B);
		int lifted = days * (A - B);
		if (V - lifted > A) {days++;}
		System.out.println(days + 1);
	}

}