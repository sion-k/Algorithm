package baekjoon.p11333;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int[][][] cache;
	
	static final int MOD = 1000000007;
	
	// 4 * n������ ī��Ʈ�� ù��° ���� �ι�° ���� b1, b2�� ��
	// ī��Ʈ�� ��ƴ���� �޿�� ����� ��
	static int dp(int n, int b1, int b2) {
		if (n == 0) return b1 == 0 && b2 == 0 ? 1 : 0;
		if (cache[n][b1][b2] != -1) return cache[n][b1][b2];
		return cache[n][b1][b2] = btk(n, 0, b1, b2, 0);
	}
	
	// 4 * n������ ī��Ʈ�� �� ���� i��° ����� �޿�� ������ ��
	// ī��Ʈ�� ��ƴ���� �޿�� ����� ��
	static int btk(int n, int i, int b1, int b2, int b3) {
		// ��� �޿� ��� dp �Լ� ȣ��
		if (i == 4) return dp(n - 1, b2, b3);
		// �̹� ������ ��� ���� ������ �Ѿ
		if ((b1 & (1 << i)) > 0) return btk(n, i + 1, b1, b2, b3);
		int sum = 0;
		// ���η� ���� ���
		if ((b2 & (1 << i)) == 0)
			sum = (sum + btk(n, i + 1, b1, b2 | (1 << i), b3 | (1 << i))) % MOD;
		// ���η� ���� ���
		if (i + 2 < 4 && (b1 & (1 << (i + 1))) == 0 && (b1 & (1 << (i + 2))) == 0)
			sum = (sum + btk(n, i + 3, b1, b2, b3)) % MOD;
		return sum;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		cache = new int[10001][1 << 4][1 << 4];
		for (int i = 0; i < cache.length; i++)
			for (int j = 0; j < cache[0].length; j++)
				Arrays.fill(cache[i][j], -1);
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) ans.append(dp(Integer.parseInt(br.readLine()), 0, 0)).append("\n");
		System.out.print(ans);
	}
}