package baekjoon.p05582;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static String A; static String B;
	static int[][] cache;

	static int dp(int i, int j) {
		if (i >= A.length() || j >= B.length()) {return 0;}
		if (cache[i][j] != -1) {return cache[i][j];}
		int max = 0;
		if (A.charAt(i) == B.charAt(j)) {
			max = Math.max(max, 1 + dp(i + 1, j + 1));
		}
		return cache[i][j] = max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		A = br.readLine(); B = br.readLine();
		cache = new int[A.length()][B.length()];
		for (int i = 0; i < A.length(); i++) {
			Arrays.fill(cache[i], -1);
		}
		int max = 0;
		for (int i = 0; i < A.length(); i++) {
			for (int j = 0; j < B.length(); j++) {
				max = Math.max(max, dp(i, j));
			}
		}
		System.out.println(max);
	}

}