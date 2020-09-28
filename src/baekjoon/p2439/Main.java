package baekjoon.p2439;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.close();
		// 매 줄마다 1에서 N개 까지의 별을 찍어야함
		for (int i = 1; i <= N; i++) {
			// 공백은 삼각형의 가장 밑 바닥 크기(N)에서 이번 줄에 찍을 별 갯수(i)를 뺀 것
			for (int j = 0; j < N - i; j++) {
				System.out.print(" ");
			}
			// i번째 행에는 i개의 별을 직는다
			for (int j = 0; j < i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

}