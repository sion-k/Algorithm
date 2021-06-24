package baekjoon.p01958;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static String A;
	static String B;
	static String C;
	static int[][][] cache;

	static final int[] di = {0, 0, 0, 1, 1, 1};
	static final int[] dj = {0, 1, 1, 0, 0, 1};
	static final int[] dk = {1, 0, 1, 0, 1, 0};

	// A[i, ] B[j, ], C[k, ]의 LCS반환
	static int dp(int i, int j, int k) {
		if (i >= A.length() || j >= B.length() || k >= C.length()) {return 0;}
		if (cache[i][j][k] != -1) {return cache[i][j][k];}
		if (A.charAt(i) == B.charAt(j) && B.charAt(j) == C.charAt(k)) {
			return cache[i][j][k] = 1 + dp(i + 1, j + 1, k + 1);
		}
		int max = 0;
		for (int m = 0; m < 6; m++) {
			int ni = i + di[m]; int nj = j + dj[m]; int nk = k + dk[m];
			max = Math.max(max, dp(ni, nj, nk));
		}
		return cache[i][j][k] = max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		A = br.readLine(); B = br.readLine(); C = br.readLine();
		cache = new int[A.length()][B.length()][C.length()];
		for (int i = 0; i < A.length(); i++) {
			for (int j = 0; j < B.length(); j++) {
				Arrays.fill(cache[i][j], -1);
			}
		}
		System.out.println(dp(0, 0, 0));
	}

}