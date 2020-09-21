package baekjoon.p1978;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// -1 미계산 0 소수가 아님 1 소수
		int[] S = new int[1000 + 1];
		Arrays.fill(S, -1);
		S[1] = 0;
		for (int i = 2; i <= 1000; i++) {
			if(S[i] != -1) {continue;}
			S[i] = 1;
			int k = i * 2;
			while(k <= 1000) {
				S[k] = 0;
				k += i;
			}
		}
		Scanner sc = new Scanner(System.in);
		//int N = sc.nextInt();
		int prime = 0;
		for(int i = 1; i <= 1000; i++) {
			if(S[i] == 1) {
				System.out.print(i + " ");
			}
			//if(S[sc.nextInt()] == 1) {prime++;}
		}
		sc.close();
		System.out.println(prime);
	}

}
