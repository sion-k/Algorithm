package baekjoon.p02306;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static char[] S;
	static int[][] cache;

	// S[i, j]�� �κ� ���ڿ� �߿��� ���� �� KOI������ ���̸� ��ȯ
	static int dp(int i, int j) {
		// ������ ���̰� 1������ ���
		if (i >= j) return 0;
		if (cache[i][j] != -1) return cache[i][j];
		int max = Math.max(dp(i + 1, j), dp(i, j - 1));
		// ���� ���� at�ų� gc�ΰ�� 2�� �߰�
		max = Math.max(max, dp(i + 1, j - 1) + (((S[i] == 'a' && S[j] == 't') || (S[i] == 'g' && S[j] == 'c')) ? 2 : 0));
		// [i, mid], [mid + 1, j]�������� ������ ������ ���
		for (int mid = i + 1; mid <= j - 2; mid++) {
			int L = dp(i, mid); int R = dp(mid + 1, j);
			// �Ѵ� KOI�����ڶ�� ���� �� �ִ�.
			if (L != 0 && R != 0) max = Math.max(max, L + R);
		}
		return cache[i][j] = max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = br.readLine().toCharArray();
		cache = new int[S.length][S.length];
		for (int i = 0; i < cache.length; i++)
			Arrays.fill(cache[i], -1);
		System.out.println(dp(0, S.length - 1));
	}

}