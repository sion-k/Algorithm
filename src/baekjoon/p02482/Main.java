package baekjoon.p02482;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int[][][] cache;
	static final int MOD = 1000000003;

	// ����ȯ�� ���ڷ� �� �켭 n���� �迭�� �������� ��
	// ù��° ���� ������ �� �ְų� ���� �� (p ? 1 : 0)
	// k���� �����ϴ� ����� ��
	static int dp(int p, int n, int k) {
		// ����ȯ�� ũ�Ⱑ 0�̸� ���� �� ��쿡�� ����� ���� �ϳ� ã��
		if (n == 0) {return k == 0 ? 1 : 0;}
		// �߰����� ����ȭ : n���� �迭���� �ִ� n / 2 + 1���� �� �� ����
		if (k > n / 2 + 1) {return 0;}
		// ���� 0�ϼ��� �ֱ� ������ -1�� �ʱ�ȭ
		if (cache[p][n][k] != -1) {return cache[p][n][k];}
		// �� ��ĭ�� ���� ���
		int sum = dp(0, n - 1, k);
		// ������ ������ �ʾҰ�, ���� �� ������ ���� ���
		if (p == 0 && k > 0) {sum = (sum + dp(1, n - 1, k - 1)) % MOD;}
		return cache[p][n][k] = sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		cache = new int[2][N + 1][K + 1];
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < N + 1; j++)
				Arrays.fill(cache[i][j], -1);
		// ����ȯ�� �Ϸķ� �켭 �����ϸ� ���������� 3���� ����� ��
		// �� ���� �Ѵ� ������ �� ����
		// 1. OX.....X
		// 2. X.....XO
		// 3. X......X
		// 1�� 2�� ���
		int sum = (2 * dp(0, N - 3, K - 1)) % MOD;
		// 3�� ���
		sum = (sum + dp(0, N - 2, K)) % MOD;
		System.out.println(sum);
	}

}