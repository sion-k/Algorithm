package baekjoon.p12960;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M; static boolean[][] S; // �̹� ������ �ִ��� ����
	static int[][] cache;
	
	// [j, M)�� ü���ǿ� ���� �� �ִ� Ÿ���� �ִ� ����
	// j��° ���� ������ �ִ��� ���δ� b�� ��Ʈ����ŷ���� ��Ÿ��
	static int dp(int j, int b) {
		if (j == M - 1) return 0; // Ÿ���� ���������� Ƣ��� �����Ƿ� �� ������ ĭ�� ��ġ �Ұ�
		if (cache[j][b] != -1) return cache[j][b];
		return cache[j][b] = btk(j, 0, b, 0);
	}
	
	static int btk(int j, int i, int b, int nb) {
		if (i == N) return dp(j + 1, nb);
		// ���� ĭ�̳� ������ ĭ�� �̹� �������ִ� ��� ���� �� ����.
		if (S[i][j] || S[i][j + 1] || (b & (1 << i)) > 0 || (nb & (1 << i)) > 0)
			return btk(j, i + 1, b, nb);
		int max = btk(j, i + 1, b, nb); // ���� �ʴ� ���
		b |= (1 << i); nb |= (1 << i);
		// ����� : ���� ĭ�� ���� ĭ�̰�, ���� Ÿ���� ���� �� �־�� ��
		if ((i + j) % 2 == 0 && i - 1 >= 0 && !S[i - 1][j] && (b & (1 << (i - 1))) == 0)
			max = Math.max(max, 1 + btk(j, i + 1, b, nb));
		// 90�� ȸ�� : ���� ĭ�� ���� ĭ�̰�, �Ʒ��� Ÿ���� ���� �� �־�� ��
		if ((i + j) % 2 == 0 && i + 1 < N && !S[i + 1][j] && (b & (1 << (i + 1))) == 0)
			max = Math.max(max, 1 + btk(j, i + 2, b | (1 << (i + 1)), nb));
		// 90�� ȸ�� : ���� ĭ�� ��� ĭ�̰�, ������ �Ʒ� Ÿ���� ���� �� �־�� ��
		if ((i + j) % 2 == 1 && i + 1 < N && !S[i + 1][j + 1])
			max = Math.max(max, 1 + btk(j, i + 1, b, nb | (1 << (i + 1))));
		// 90�� ȸ�� : ���� ĭ�� ��� ĭ�̰�, ������ �� Ÿ���� ���� �� �־�� ��
		if ((i + j) % 2 == 1 && i - 1 >= 0 && !S[i - 1][j + 1] && (nb & (1 << (i - 1))) == 0)
			max = Math.max(max, 1 + btk(j, i + 1, b, nb | (1 << (i - 1))));
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		S = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String row = br.readLine();
			for (int j = 0; j < M; j++) S[i][j] = row.charAt(j) == 'X';
		}
		cache = new int[M][1 << N];
		for (int i = 0; i < M; i++) Arrays.fill(cache[i], -1);
		System.out.println(dp(0, 0));
	}
	
}