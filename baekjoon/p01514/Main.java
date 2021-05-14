package baekjoon.p01514;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N;
	// S : �ʱ���� G : ��ǥ
	static int[] S; static int[] G;
	static int[][][][][] cache;

	static final int INF = 987654321;

	// here�� ���� diff��ŭ ������ �� ����� ��ȯ�Ѵ� (������ ��� �����ϰ� ������ ��)
	static int rotate(int here, int diff, int d) {
		return d > 0 ? (here + diff + 10) % 10 : (here - diff + 10) % 10;
	}

	// S[i, )�� ���� S[i], S[i + 1], S[i + 2]�� ���� ���� p, q, r�϶�
	// �׸��� ȸ�� ������ �������� �������� d (0 ����, 1 ����)
	// �ڹ����� Ǯ�� ���� �ּ� ȸ�� Ƚ��
	static int dp(int i, int p, int q, int r, int d) {
		if (i == N) return 0;
		// �̹� �������ִ� ��� �Ѿ��
		if (p == G[i])
			return cache[i][p][q][r][d] = Math.min
			(dp(i + 1, q, r, S[i + 3], 0), dp(i + 1, q, r, S[i + 3], 1));
		if (cache[i][p][q][r][d] != -1) return cache[i][p][q][r][d];
		cache[i][p][q][r][d] = INF;
		// S[i]���� �� �� �ִ� �ൿ�� ��ũ�� ���� +- 1, 2, 3��ȭ��Ű�� ��
		// �׸��� 1, 2, 3���� ��ũ�� ���� ���� �� �ִ�
		for (int diff = 1; diff <= 3; diff++) {
			int np = rotate(p, diff, d); int nq = rotate(q, diff, d); int nr = rotate(r, diff, d);
			// 1���� ��ũ�� ������ ���
			cache[i][p][q][r][d] = Math.min(cache[i][p][q][r][d], 1 + dp(i, np, q, r, d));
			// 2���� ��ũ�� ���� ������ ���
			if (i <= N - 2)
				cache[i][p][q][r][d] = Math.min(cache[i][p][q][r][d], 1 + dp(i, np, nq, r, d));
			// 3���� ��ũ�� ���� ������ ���
			if (i <= N - 3)
				cache[i][p][q][r][d] = Math.min(cache[i][p][q][r][d], 1 + dp(i, np, nq, nr, d));
		}
		return cache[i][p][q][r][d];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		S = new int[N + 3]; G = new int[N + 3];
		String line = br.readLine();
		for (int i = 0; i < N; i++)
			S[i] = line.charAt(i) - '0';
		line = br.readLine();
		for (int i = 0; i < N; i++)
			G[i] = line.charAt(i) - '0';
		for (int i = N; i < N + 3; i++)
			S[i] = G[i] = 0;
		cache = new int[N][10][10][10][2];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < 10; j++)
				for (int k = 0; k < 10; k++)
					for (int l = 0; l < 10; l++)
						Arrays.fill(cache[i][j][k][l], -1);
		System.out.println(Math.min(dp(0, S[0], S[1], S[2], 0), dp(0, S[0], S[1], S[2], 1)));
	}

}