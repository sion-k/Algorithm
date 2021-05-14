package baekjoon.p08393;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.close();
		System.out.println(N * (1 + N)/2);
	}
}
