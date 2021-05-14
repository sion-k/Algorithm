package codeforce.r1506;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C {
	static String A; static String B;
	static int[][][][] cache;
	
	static int dp(int ai, int aj, int bi, int bj) {
		if (aj - ai == bj - bi) {
			boolean same = true;
			for (int k = 0; k < aj - ai; k++)
				if (A.charAt(ai + k) != B.charAt(bi + k)) {same = false; break;}
			if (same) return 0;
		}
		if (cache[ai][aj][bi][bj] != 0) return cache[ai][aj][bi][bj];
		int min = 100;
		// A
		if (aj - ai > 0) {
			min = Math.min(min, 1 + dp(ai + 1, aj, bi, bj));
			min = Math.min(min, 1 + dp(ai, aj - 1, bi, bj));
		}
		// B
		if (bj - bi > 0) {
			min = Math.min(min, 1 + dp(ai, aj, bi + 1, bj));
			min = Math.min(min, 1 + dp(ai, aj, bi, bj - 1));
		}
		return cache[ai][aj][bi][bj] = min;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			A = br.readLine();
			B = br.readLine();
			if (A.equals(B)) {System.out.println(0); continue;}
			cache = new int[A.length() + 1][A.length() + 1][B.length() + 1][B.length() + 1];
			System.out.println(dp(0, A.length(), 0, B.length()));
		}
	}
	
}