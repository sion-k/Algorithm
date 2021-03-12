package codeforce.c1490;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C {
	
	static boolean check(long x) {
		for (long a = 1; a <= 10000; a++)
			for (long b = a; b <= 10000; b++) {
				if (x == a * a * a + b * b * b) return true;
			}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			ans.append(check(Long.parseLong(br.readLine())) ? "YES" : "NO").append("\n");
		}
		System.out.print(ans.toString().trim());
	}

}