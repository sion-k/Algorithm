package baekjoon.p02240;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int T;
	static int[] P; // t�ʿ� [1, 2]�� �ϳ��� ��������
	static int[][][] cache;

	// t�ʿ� p�� ���� �� ���� �̵� Ƚ���� w�� ������ �ִ� �ִ� ����
	static int dp(int t, int p, int w) {
		if (t > T) {return 0;}
		if (w == 0) {
			return cache[t][p][w] =
			(P[t] == p ? 1 : 0) + dp(t + 1, p, 0);
		}
		if (cache[t][p][w] != -1) {return cache[t][p][w];}
		// �������� ������ �ְų� �����̰ų�
		return cache[t][p][w] = (P[t] == p ? 1 : 0) +
		Math.max(dp(t + 1, p, w), dp(t + 1, p % 2 + 1, w - 1));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		T = Integer.parseInt(st.nextToken());
		P = new int[T + 1];
		int w = Integer.parseInt(st.nextToken());
		cache = new int[T + 1][3][w + 1];
		for (int i = 0; i <= T; i++) {
			for (int j = 1; j <= 2; j++) {
				Arrays.fill(cache[i][j], -1);
			}
		}
		for (int i = 1; i <= T; i++) {P[i] = Integer.parseInt(br.readLine());}
		System.out.println(dp(0, 1, w));
		br.close();
	}

}