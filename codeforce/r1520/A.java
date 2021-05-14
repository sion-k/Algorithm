package codeforce.r1520;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			boolean[] check = new boolean[26];
			String S = br.readLine();
			boolean ok = true;
			for (int i = 0; i < N; i++) {
				int ch = S.charAt(i) - 'A';
				if (check[ch] && S.charAt(i) != S.charAt(i - 1)) ok = false;
				check[ch] = true;
			}
			ans.append(ok ? "YES" : "NO").append("\n");
		}
		System.out.print(ans);
	}

}