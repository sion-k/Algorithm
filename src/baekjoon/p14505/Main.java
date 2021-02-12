package baekjoon.p14505;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static String S;
	static int[][] cache;

	// S[i, j]구간에 존재하는 팰린드롬 부분 수열의 개수
	static int dp(int i, int j) {
		// 구간의 길이가 3미만인 경우는 기저사례로 처리
		if (i == j) return 1;
		if (i + 1 == j) return S.charAt(i) == S.charAt(j) ? 3 : 2;
		if (cache[i][j] != -1) return cache[i][j];
		return cache[i][j] = dp(i + 1, j) + dp(i, j - 1) + (S.charAt(i) == S.charAt(j) ? 1 : -dp(i + 1, j - 1));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = br.readLine();
		cache = new int[S.length()][S.length()];
		for (int i = 0; i < cache.length; i++)
			Arrays.fill(cache[i], -1);
		System.out.println(dp(0, S.length() - 1));
	}

}