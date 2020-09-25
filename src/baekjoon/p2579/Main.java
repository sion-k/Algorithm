package baekjoon.p2579;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int[] S;
	static int[] cache;

	// i��° ����� ������� �� ���� �� �ִ� �ִ� ���� (������ �����´�)
	static int dp(int i) {
		// ����� �� ������ ���
		if (i >= N) {return 0;}
		// ���� ����� ���� ���
		if (i == N - 1) {return S[i];}
		if (cache[i] != 0) {return cache[i];}
		
		return cache[i] = S[i] + Math.max(S[i + 1] + dp(i + 3), dp(i + 2));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		S = new int[N];
		cache = new int[N];
		for (int i = N - 1; i >= 0; i--) {
			S[i] = Integer.parseInt(br.readLine());
		}
		br.close();
		System.out.println(dp(0));
	}

}
