package baekjoon.p01929;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int MAX_N;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt(); int N = sc.nextInt();
		sc.close();
		MAX_N = N;
		int[] S = new int[MAX_N + 1];
		Arrays.fill(S, -1); // -1은 계산 안됨
		S[1] = 0; // 0은 소수가 아님 1은 소수임
		for (int i = 2; i <= MAX_N; i++) {
			if (S[i] != -1) {continue;}
			S[i] = 1;
			int k = i * 2;
			while (k <= MAX_N) {
				S[k] = 0;
				k += i;
			}
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = M; i <= N; i++) {
			if (S[i] == 1) {bw.write(String.valueOf(i)); bw.newLine();}
		}
		bw.close();
	}
	
}