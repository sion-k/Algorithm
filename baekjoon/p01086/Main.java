package baekjoon.p01086;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N; static int[][] S;// (S[i] % MOD, |S[i]|)
	static long[][] cache;
	static int MOD;
	static int[] D; // 10^i �� MOD�� ���� �������� ����

	// ����� ������ b�� ���ݱ��� ���� MOD�� ���� �������� remainder�϶�, N���� ������ ��� ����� �ٿ��� ���� ����
	// K�� ������ �������� ����� ��
	static long dp(int b, int remainder) {
		// ��� ������ ���Ҹ� ����� �ٿ����� ������ ���������� Ȯ��
		if (b == ((1 << N) - 1)) return remainder == 0 ? 1 : 0;
		if (cache[b][remainder] != -1) return cache[b][remainder];
		long sum = 0;
		for (int i = 0; i < N; i++)
			if ((b & (1 << i)) == 0)
				sum += dp(b | (1 << i), ((remainder * D[S[i][1]]) % MOD + S[i][0] % MOD) % MOD);
		return cache[b][remainder] = sum;
	}

	static long gcd(long a, long b) {
		if (b == 0) return a;
		return gcd(b, a % b);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String[] temp = new String[N];
		for (int i = 0; i < N; i++) temp[i] = br.readLine();
		MOD = Integer.parseInt(br.readLine());
		// D �ʱ�ȭ
		D = new int[50 * 15]; D[0] = 1;
		for (int i = 1; i < D.length; i++) D[i] = (D[i - 1] * 10) % MOD;
		S = new int[N][2];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < temp[i].length(); j++)
				S[i][0] = (S[i][0] + ((temp[i].charAt(temp[i].length() - j - 1) - '0') * D[j]) % MOD) % MOD; 
			S[i][1] = temp[i].length();
		}
		cache = new long[1 << N][MOD];
		for (int i = 0; i < cache.length; i++) Arrays.fill(cache[i], -1);
		// (a / b)�� ���м��� ��Ÿ����
		long a = dp(0, 0); long b = 1;
		for (int i = 2; i <= N; i++) b *= i;
		long t = gcd(a, b);
		a /= t; b /= t;
		System.out.println(a + "/" + b);
	}

}