package codeforce.c1490;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C {
	
	static long bin(long x) {
		long lo = 0; long hi = 10000;
		while (lo + 1 < hi) {
			long mid = (lo + hi) / 2;
			if ((mid * mid * mid) < x) lo = mid;
			else hi = mid;
		}
		return hi;
	}
	
	static boolean check(long x) {
		long a = 1;
		while (a * a * a < x) {
			long b = bin(x - (a * a * a));
			if (a * a * a + b * b * b == x) return true;
			a++;
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