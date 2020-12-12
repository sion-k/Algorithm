package baekjoon.p02666;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N; static int M; static int[] S;
	static int[][][] cache;

	// �� ���幮�� (i, j)�� ���� �� k��° ���幮�� ���� ���ؼ� �̵��ؾ��ϴ� �ּ� �̵� ��
	static int dp(int i, int j, int k) {
		if (k == M) {return 0;}
		if (cache[i][j][k] != -1) {return cache[i][j][k];}
		if (i == S[k] || j == S[k])
			return cache[i][j][k] = dp(i, j, k + 1);
		// ����, ������ �� ���幮�� �̵���Ű�� ���
		return cache[i][j][k] =
		Math.min(Math.abs(S[k] - i) + dp(S[k], j, k + 1),
				 Math.abs(S[k] - j) + dp(i, S[k], k + 1));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(br.readLine()); S = new int[M];
		for (int i = 0; i < M; i++)
			S[i] = Integer.parseInt(br.readLine()) - 1;
		cache = new int[N][N][M];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				Arrays.fill(cache[i][j], -1);
		System.out.println(dp(a - 1, b - 1, 0));
	}

}