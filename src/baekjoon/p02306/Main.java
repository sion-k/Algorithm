package baekjoon.p02306;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static char[] S;
	static int[][] cache;

	// S[i, j]의 부분 문자열 중에서 가장 긴 KOI유전자 길이를 반환
	static int dp(int i, int j) {
		// 구간의 길이가 1이하인 경우
		if (i >= j) return 0;
		if (cache[i][j] != -1) return cache[i][j];
		int max = Math.max(dp(i + 1, j), dp(i, j - 1));
		// 양쪽 끝이 at거나 gc인경우 2를 추가
		max = Math.max(max, dp(i + 1, j - 1) + (((S[i] == 'a' && S[j] == 't') || (S[i] == 'g' && S[j] == 'c')) ? 2 : 0));
		// [i, mid], [mid + 1, j]구간으로 반으로 나누는 경우
		for (int mid = i + 1; mid <= j - 2; mid++) {
			int L = dp(i, mid); int R = dp(mid + 1, j);
			// 둘다 KOI유전자라면 이을 수 있다.
			if (L != 0 && R != 0) max = Math.max(max, L + R);
		}
		return cache[i][j] = max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = br.readLine().toCharArray();
		cache = new int[S.length][S.length];
		for (int i = 0; i < cache.length; i++)
			Arrays.fill(cache[i], -1);
		System.out.println(dp(0, S.length - 1));
	}

}