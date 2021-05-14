package baekjoon.p10740;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N; static int[][] S;
	static int[][][] cache;
	
	static final int INF = 987654321;
	
	// j��° �������� �����ؼ�, ������ prev��° ����� ������ �þ��� ��
	// ������ �����ϴ°��� ���´��� ���θ� b�� ������ ��, ���̵��� �ּ� ��
	static int dp(int j, int prev, int b) {
		if (j == N) return (b | (1 << prev)) == 15 ? 0 : INF;
		if (cache[j][prev][b] != 0) return cache[j][prev][b];
		int min = INF;
		for (int i = 1; i <= 3; i++) {
			// ���⸦ ������ ���� ����� ����
			if ((b & (1 << i)) == 0) {
				if (i == prev) min = Math.min(min, S[i][j] + dp(j + 1, i, b));
				else min = Math.min(min, S[i][j] + dp(j + 1, i, b | (1 << prev)));
			}
		}
		return cache[j][prev][b] = min;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		S = new int[4][N];
		for (int i = 1; i <= 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) S[i][j] = Integer.parseInt(st.nextToken());
		}
		cache = new int[N][4][1 << 4];
		System.out.println(dp(0, 0, 0));
	}
	
}