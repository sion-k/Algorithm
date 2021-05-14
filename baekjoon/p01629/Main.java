package baekjoon.p01629;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int MOD;

	static long pow(int a, int b) {
		if (b == 0) return 1;
		if (b % 2 == 1) return (a * pow(a, b - 1)) % MOD;
		long f = pow(a, b / 2);
		return (f * f) % MOD;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		MOD = Integer.parseInt(st.nextToken());
		System.out.println(pow(A, B));
	}

}