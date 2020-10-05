package baekjoon.p02875;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int K = sc.nextInt();
		int T = 0;
		while (N >= 2 && M >= 1) {
			N -= 2;
			M--;
			T++;
		}
		int left = N + M;
		while (K > 0) {
			if (left == 0) {
				T--;
				left += 3;
			}
			left--;
			K--;
		}
		System.out.println(T);
		sc.close();
	}

}
