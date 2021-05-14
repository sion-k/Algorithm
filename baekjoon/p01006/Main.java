package baekjoon.p01006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, W;
	static int[][] S;
	static int[][][] cache;
	
	// i��° ���� Ŀ���� ���ΰ� b1���� �־�����
	// N - 1��° ���� ���������� Ŀ���� ���ΰ� b2�� �־��� �� [i, N)�� ��� Ŀ���ϴ� �ּ� ����
	static int dp(int i, int b1, int b2) {
		if (i == N) return 0;
		if (cache[i][b1][b2] != 0) return cache[i][b1][b2];
		return cache[i][b1][b2] = btk(i, 0, b1, b2, 0);
	}
	
	static int btk(int i, int j, int b1, int b2, int nb) {
		if (j == 2) return dp(i + 1, nb, b2);
		// �̹� Ŀ���Ǿ��ִ� ��� �Ѿ
		if ((b1 & (1 << j)) > 0) return btk(i, j + 1, b1, b2, nb);
		// ������ ���� ��쵵 Ȯ��
		if (i == N - 1 && (b2 & (1 << j)) > 0) return btk(i, j + 1, b1, b2, nb);
		// (i, j)�� Ŀ���ϴ� ���
		int min = 1 + btk(i, j + 1, b1, b2, nb);
		// (i + 1, j)�� ���� Ŀ���ϴ� ���
		if (i + 1 < N && S[i][j] + S[i + 1][j] <= W) {
			if (i + 1 != N - 1 || !((b2 & (1 << j)) > 0))
				min = Math.min(min, 1 + btk(i, j + 1, b1, b2, nb | (1 << j)));
		}
		// (i, j + 1)�� ���� Ŀ���ϴ� ���
		if (j + 1 < 2 && S[i][j] + S[i][j + 1] <= W) {
			if (i != N - 1 || !((b2 & (1 << (j + 1))) > 0)) {
				min = Math.min(min, 1 + btk(i, j + 2, b1, b2, nb));
			}
		}
		return min;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			S = new int[N][2];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) S[i][0] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) S[i][1] = Integer.parseInt(st.nextToken());
			cache = new int[N][4][4];
			int min = dp(0, 0, 0);
			if (S[0][0] + S[N - 1][0] <= W) min = Math.min(min, 1 + dp(0, 1, 1));
			if (S[0][1] + S[N - 1][1] <= W) min = Math.min(min, 1 + dp(0, 2, 2));
			if (S[0][0] + S[N - 1][0] <= W && S[0][1] + S[N - 1][1] <= W) min = Math.min(min, 2 + dp(0, 3, 3));
			ans.append(min).append("\n");
		}
		System.out.print(ans);
	}

}
