package baekjoon.p10597;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[] S;
	static int[] ans = null;

	static void btk(boolean[] used, int i, int[] picked, int j) {
		if (ans != null) return;
		if (i == S.length) {
			ans = picked.clone();
			return;
		}
		// 1글자 매칭
		int p1 = S[i];
		if (!used[p1]) {
			used[p1] = true;
			picked[j] = p1;
			btk(used, i + 1, picked, j + 1);
			used[p1] = false;
		}
		// 2글자 매칭
		if (i <= S.length - 2) {
			int p2 = 10 * S[i] + S[i + 1];
			if (p2 <= used.length - 1 && !used[p2]) {
				used[p2] = true;
				picked[j] = p2;
				btk(used, i + 2, picked, j + 1);
				used[p2] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String P = br.readLine();
		S = new int[P.length()];
		for (int i = 0; i < P.length(); i++)
			S[i] = (P.charAt(i) - '0');
		int N = 0;
		if (P.length() <= 9)
			N = P.length();
		else
			N = (P.length() - 9) / 2 + 9;
		boolean[] used = new boolean[N + 1];
		int[] picked = new int[N];
		btk(used, 0, picked, 0);
		for (int a : ans)
			System.out.print(a + " ");
	}

}