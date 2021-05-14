package baekjoon.p01563;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int[][][] cache;
	static final int MOD = 1000000;

	// i��° �ϱ��� ���� p�� �������� q�� �Ἦ ���� ��, ����� ��
	static int dp(int i, int p, int q) {
		if (i >= N) return 1;
		if (cache[i][p][q] != 0) return cache[i][p][q];
		// �⼮�ϴ� ���, ���� �Ἦ�� �ʱ�ȭ ��
		int sum = dp(i + 1, p , 0);
		// ���� �����ϸ� ����
		if (p + 1 < 2) sum = (sum + dp(i + 1, p + 1, 0)) % MOD;
		// �Ἦ �����ϸ� �Ἦ
		if (q + 1 < 3) sum = (sum + dp(i + 1, p, q + 1)) % MOD;
		return cache[i][p][q] = sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cache = new int[N][2][3];
		System.out.println(dp(0, 0, 0));
	}

}