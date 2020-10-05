package baekjoon.p01978;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	private static int MAX_OF_INPUT = 1000;
	
	public static void main(String[] args) {
		int[] S = new int[MAX_OF_INPUT + 1];
		Arrays.fill(S, -1);
		S[1] = 0;
		for (int i = 2; i <= MAX_OF_INPUT; i++) {
			if (S[i] != -1) {
				continue;
			}
			S[i] = 1;
			int k = i * 2;
			while (k <= MAX_OF_INPUT) {
				S[k] = 0;
				k += i;
			}
		}
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int prime = 0;
		for (int i = 0; i < N; i++) {
			if (S[sc.nextInt()] == 1) {
				prime++;
			}
		}
		sc.close();
		System.out.println(prime);
	}
}
