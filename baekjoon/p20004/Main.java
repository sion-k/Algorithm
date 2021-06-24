package baekjoon.p20004;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int[][] cache;

	// i번째 부터 부르기 시작할 때 최대 n개 부를수 있을 때 이길 수 있는지 여부
	static int canBeat(int i, int n) {
		if (i >= 31) {return 0;}
		if (cache[i][n] != -1) {return cache[i][n];}
		boolean beatable = false;
		for (int next = i + 1; next <= i + n; next++) {
			if (canBeat(next, n) == 0) {beatable = true; break;}
		}
		return cache[i][n] = beatable ? 1 : 0;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int A = Integer.parseInt(br.readLine());
		cache = new int[32][32];
		for (int c = 0; c < 32; c++) {Arrays.fill(cache[c], -1);}
		for (int n = 1; n <= A; n++) {
			if(canBeat(1, n) == 0) {System.out.println(n);}
		}
	}

}