package baekjoon.p02169;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {
	static final int NINF = -987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] MAP = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				MAP[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[][][] dp = new int[3][N][M];
		// ���� ��� : ������ �� �Ʒ�
		dp[0][N - 1][M - 1] = dp[1][N - 1][M - 1] = dp[2][N - 1][M - 1] =
		MAP[N - 1][M - 1];
		// �� �Ʒ� �� ä���
		// m = 1 �������θ� ������ �� ���� ��
		for (int x = 0; x < M - 1; x++)
			dp[1][N - 1][x] = NINF;
		// m = 2 ���������θ� ������ �� ���� ��
		for (int x = M - 2; x >= 0; x--)
			dp[2][N - 1][x] = dp[2][N - 1][x + 1] + MAP[N - 1][x];
		// m = 0 �������� ������ �� ���� ��
		// x = 0�ΰ�� ����, M�� 1�� ��쵵 ����
		if (M >= 2) dp[0][N - 1][0] += dp[2][N - 1][1] + MAP[N - 1][0];
		for (int x = 1; x < M - 1; x++)
			dp[0][N - 1][x] = Math.max(dp[1][N - 1][x - 1], dp[2][N - 1][x + 1]) +
			MAP[N - 1][x];
		// x = M - 1�ΰ��� ������ʿ��� ó����
		// �� �Ʒ� �� ä��� ��
		// �� �Ʒ� ���� ������ �������� ä�� �� ����
		for (int y = N - 2; y >= 0; y--) {
			// m = 1
			// x = 0�� ��� ����
			dp[1][y][0] = dp[0][y + 1][0] + MAP[y][0];
			for (int x = 1; x < M; x++)
				dp[1][y][x] = Math.max(dp[0][y + 1][x], dp[1][y][x - 1]) +
				MAP[y][x];
			// m = 2
			// x = M - 1�� ��� ����
			dp[2][y][M - 1] = dp[0][y + 1][M - 1] + MAP[y][M - 1];
			for (int x = M - 2; x >= 0; x--)
				dp[2][y][x] = Math.max(dp[0][y + 1][x], dp[2][y][x + 1]) +
				MAP[y][x];
			// m = 1, 2�� ������ m = 0 ä��� ����
			// x = 0�� ��� ����, M�� 1�� ��쵵 ����
			dp[0][y][0] = Math.max(dp[0][y + 1][0], M >= 2 ? dp[2][y][1] : NINF) +
			MAP[y][0];
			for (int x = 1; x < M - 1; x++)
				dp[0][y][x] = Math.max(dp[0][y + 1][x],
				Math.max(dp[1][y][x - 1], dp[2][y][x + 1])) +
				MAP[y][x];
			// x = M - 1�� ��� ����, M�� 1�� ��쵵 ����
			dp[0][y][M - 1] = Math.max(dp[0][y + 1][M - 1], M >= 2 ? dp[1][y][M - 2] : NINF) +
			MAP[y][M - 1];
		}
		System.out.println(dp[0][0][0]);
	}

}