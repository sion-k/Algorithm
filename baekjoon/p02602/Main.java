package baekjoon.p02602;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static char[] S; static int L; // 문자열 길이
	static char[][] B; static int N; // 다리 길이

	static int[][][] cache;

	// (y, x, k)다리를 건너가는 방법의 수 반환
	static int dp(int y, int x, int k) {
		if (k == L - 1) {return 1;} // 문자열의 끝에 도착하면 방법을 하나 찾음
		if (cache[y][x][k] != -1) {return cache[y][x][k];}
		int sum = 0;
		int ny = (y + 1) % 2;
		for (int nx = x + 1; nx < N; nx++)
			if (B[ny][nx] == S[k + 1])
				sum += dp(ny, nx, k + 1);
		return cache[y][x][k] = sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = br.readLine().toCharArray(); L = S.length;
		B = new char[2][];
		B[0] = br.readLine().toCharArray(); N = B[0].length;
		B[1] = br.readLine().toCharArray();
		cache = new int[2][N][L];
		for (int y = 0; y < 2; y++)
			for (int x = 0; x < N; x++)
				Arrays.fill(cache[y][x], -1);
		int sum = 0;
		for (int y = 0; y < 2; y++)
			for (int x = 0; x < N; x++)
				if (B[y][x] == S[0])
					sum += dp(y, x, 0);
		System.out.println(sum);
	}

}