package baekjoon.p09936;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N; static int[][] S;
	static int[][][] cache;

	static final int NULL = -2000000000;
	
	// i��° ����� ü������ k���� ���̳�� ä��������
	// i��° �࿡ �̹� ä�����ִ� ĭ�� ���δ� 3��Ʈ�� f�� ��Ÿ����
	static int dp(int i, int k, int f) {
		if (i == N) return k == 0 ? 0 : NULL;
		if (cache[i][k][f] != NULL) return cache[i][k][f];
		return cache[i][k][f] = btk(i, 0, k, f, 0);
	}
	
	// i��° �� j��° ������ k���� ���̳븦 ���� ��
	// i��° ���� ������ f�� i+1��° ���� ������ nf���� ��
	// dp(i + 1, ...)�� ȣ���Ͽ� ���� �� �ִ� �ִ밪
	static int btk(int i, int j, int k, int f, int nf) {
		if (j == 3) return dp(i + 1, k, nf); // 3ĭ�� ��� �õ��Ѱ��
		if (k == 0) return 0; // ���̳밡 �� �̻� ������ ���� �� ����
		if ((f & (1 << j)) > 0) return btk(i, j + 1, k, f, nf); // ���� �� ���� ��� ���� ĭ���� ��� ȣ��
		// �ȳ��� ���
		int max = btk(i, j + 1, k, f, nf);
		// �����ϴٸ� ���η� ���� ���
		if (i + 1 < N) max = Math.max(max, S[i][j] + S[i + 1][j] + btk(i, j + 1, k - 1, f, nf | (1 << j)));
		// �����ϴٸ� ���η� ���� ���
		if (j + 1 < 3 && (f & (1 << (j + 1))) == 0) max = Math.max(max, S[i][j] + S[i][j + 1] + btk(i, j + 2, k - 1, f, nf));
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		S = new int[N][3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 3; j++) S[i][j] = Integer.parseInt(st.nextToken());
		}
		cache = new int[N][K + 1][8];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < K + 1; j++)
				Arrays.fill(cache[i][j], NULL);
		System.out.println(dp(0, K, 0));
	}

}