package baekjoon.p14276;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static int[][][][] cache;
	
	static final int MOD = 1000000007;
	
	// start��° ���ÿ��� length���� �̻��� ���θ� m�� �Ǽ��Ϸ��� �� ��,
	// [start, start + K]�� ���°� b�϶� ����� ��
	static int dp(int start, int length, int m, int b) {
		// �� ������ ������ ��� �� �̻� ���� ������ ����, b�� ��Ÿ�� ���õ��� ����� �������� ������ ��� ¦������ ��츦 �ϳ� ã��
		// �� �̻� ���� ������ ���� ���, b�� ��Ÿ�� ���õ��� ����� �������� ������ ��� ¦������ ��츦 �ϳ� ã��
		if (start >= N - 1 || m == 0) return m == 0 && b == 0 ? 1 : 0;
		// ������ ���̰� K�� �Ѿ��ų�, ������ ����� ���� ���ÿ� ���ؼ� �õ�
		if (length > K || start + length >= N) return (b & 1) == 0 ? dp(start + 1, 1, m, b >> 1) : 0;
		if (cache[start][length][m][b] != -1) return cache[start][length][m][b];
		// length������ ������ ���� �ʴ� ���
		int sum = dp(start, length + 1, m, b);
		// length������ ������ �ϳ� �� ���� ���
		sum = (sum + dp(start, length, m - 1, b ^ 1 ^ (1 << length))) % MOD;
		return cache[start][length][m][b] = sum;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		cache = new int[N][K + 1][M + 1][1 << (K + 1)];
		for (int i = 0; i < cache.length; i++)
			for (int j = 0; j < cache[i].length; j++)
				for (int k = 0; k < cache[i][j].length; k++)
					Arrays.fill(cache[i][j][k], -1);
		System.out.println(dp(0, 1, M, 0));
	}

}