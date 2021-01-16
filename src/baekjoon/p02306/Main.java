package baekjoon.p02306;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static char[] S;
	// S[i, j)가 KOI 유전자이면 1 아니면 0
	static int[][] cache;

	// 부분 문자열 S[i, j)가 KOI 유전자인지 반환
	static boolean dp(int i, int j) {
		// 빈 문자열은 KOI 유전자이다
		if (i == j) return true;
		// KOI 유전자의 길이는 무조건 짝수
		if ((j - i) % 2 != 0) return false;
		if (cache[i][j] != -1) return cache[i][j] == 1;
		// aXt, gXc
		if ((S[i] == 'a' && S[j - 1] == 't') || (S[i] == 'g' && S[j - 1] =='c'))
			if (dp(i + 1, j - 1)) {cache[i][j] = 1; return true;}
		// XY
		for (int k = 2; i + k < j; k += 2)
			if (dp(i, i + k) && dp(i + k, j)) {
				cache[i][j] = 1;
				return true;
			}
		cache[i][j] = 0;
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {

		S = br.readLine().toCharArray();
		cache = new int[S.length + 1][S.length + 1];
		for (int i = 0 ; i < cache.length; i++)
			Arrays.fill(cache[i], -1);
		System.out.println(dp(0, S.length));

		}
	}

}