package baekjoon.p10817;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] S = new int[3];
		for(int i = 0; i < 3; i++) {
			S[i] = sc.nextInt();
		}
		sc.close();
		Arrays.sort(S);
		System.out.println(S[1]);
	}

}