package baekjoon.p01315;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[][] S;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		S = new int[N][3];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) S[i][j] = Integer.parseInt(st.nextToken());
		}

		// ������ �� ����Ʈ�� �����ϰ� ���� (i, j)�� ���ڱ� �ȴٸ� �� �� �ִ� ����Ʈ ��
		int[][] cnt = new int[1001][1001];
		// �ʱ� ���� (1, 1)���� (i, j)�� ���� �������� ���� ����
		boolean[][] reachable = new boolean[1001][1001];
		reachable[1][1] = true;
		// ������ �� ����Ʈ�� �����ϰ� ���� (i, j)�� ���ڱ� �ȴٸ� �� �� �ִ� ����Ʈ�� �� ���� (i, j)�� �������ص� ���� ����Ʈ
		int[][] point = new int[1001][1001];

		// ��� ������ (i, j)�� ���ؼ� Ȯ���غ���
		for (int i = 1; i <= 1000; i++) {
			for (int j = 1; j <= 1000; j++) {
				// cnt[i][j]�� ����غ���
				int sum = 0;
				for (int q = 0; q < N; q++)
					if (S[q][0] <= i || S[q][1] <= j) {
						sum += S[q][2]; cnt[i][j]++;
					}
				// point[i][j]�� ���
				point[i][j] = sum - i - j + 2;
				// (i, j)�� ������ ���� ������ ���°�, ���� ����Ʈ�� ������ �̿��� ���µ� ���� ����
				if (reachable[i][j] && point[i][j] > 0) {
					if (i + 1 <= 1000) reachable[i + 1][j] = true;
					if (j + 1 <= 1000) reachable[i][j + 1] = true;
				}
			}
		}
		int max = 0;
		for (int i = 1; i <= 1000; i++)
			for (int j = 1; j <= 1000; j++)
				if (reachable[i][j])
					max = Math.max(max, cnt[i][j]);
		System.out.println(max);
	}

}