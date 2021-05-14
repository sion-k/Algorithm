package baekjoon.p14390;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static boolean[][] S;	
	static int[][] cache;

	static final int INF = 987654321;
	
	// i��° ����� Ÿ���� ä�� �� �� �ٴ��� ��� ä��µ� �ʿ��� Ÿ���� �ּ� ����
	// b�� �ٷ� ���� ���� ĭ�� ���θ�� Ÿ�Ϸ� ä������ -> 0, ���� ������� ä������ -> 1 ��Ÿ����
	static int dp(int i, int b) {
		if (i == N) return 0;
		if (cache[i][b] != -1) return cache[i][b];
		return cache[i][b] = btk(i, 0, b, 0);
	}
	
	static int btk(int i, int j, int b, int nb) {
		if (j == M) return dp(i + 1, nb);
		if (S[i][j]) return btk(i, j + 1, b, nb);
		int min = INF;
		// ���η� ���� ���
		// ���� ������ ���η� ���� ��� �� Ÿ���� ���� �ʿ� ���� ������ ���� Ÿ���� �ø��� ��
		if (j - 1 >= 0 && !S[i][j - 1] && (nb & (1 << (j - 1))) == 0)
			min = Math.min(min, btk(i, j + 1, b, nb));
		else 
			min = Math.min(min, 1 + btk(i, j + 1, b, nb));
		// ���η� ���� ���
		nb |= (1 << j);
		// ���� ������ ���η� ���� ��� �� Ÿ���� ���� �ʿ� ���� ������ ���� Ÿ���� �ø��� ��
		if (i - 1 >= 0 && !S[i - 1][j] && (b & (1 << j)) > 0)
			min = Math.min(min, btk(i, j + 1, b, nb));
		else
			min = Math.min(min, 1 + btk(i, j + 1, b, nb));
		return min;
	}
	
	static void rotate() {
		boolean[][] ret = new boolean[M][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				ret[j][N - i - 1] = S[i][j];
		int temp = N; N = M; M = temp;
		S = ret;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		S = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String row = br.readLine();
			for (int j = 0; j < M; j++) S[i][j] = row.charAt(j) == '#';
		}
		if (N < M) rotate();
		cache = new int[N][1 << M];
		for (int i = 0; i < N; i++) Arrays.fill(cache[i], -1);
		System.out.println(dp(0, 0));
	}

}