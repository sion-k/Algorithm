package baekjoon.p15684;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static boolean[][] S; // N, M ���μ��� �ִ��� ����

	static int min = 4;

	// i��° �� j��° ������ ���� ���� ���´�
	// ���ݱ��� l���� ���μ��� �߰����� ��,
	// �߰��ؾ��ϴ� �ּ��� ���μ� ������ ����
	static void btk(int i, int j, int l) {
		if (l >= min) return; // 3���� �Ѱ� ���� �� ����.
		// �� �Ʒ� �� �������̸� ��
		if (i == N - 1 && j >= M - 1) {
			if (sat()) min = Math.min(min, l);
			return;
		}
		// �� ������ ������ ���� �� ����
		if (j >= M - 1) {
			btk(i + 1, 0, l);
			return;
		}
		// ���μ��� ���� ���
		if (!S[i][j]) {
			S[i][j] = true;
			btk(i, j + 2, l + 1);
			S[i][j] = false;
		}
		// ���μ��� ���� �ʴ� ���
		btk(i, j + 1, l);
	}

	// i�� ���μ��� ����� i���� �������� Ȯ��
	static boolean sat() {
		for (int j = 0; j < M; j++)
			if (dest(0, j) != j) return false;
		return true;
	}

	// j��° ���� ���� i�� ��ġ���� �������� �����ϴ� ��ȣ ��ȯ
	static int dest(int i, int j) {
		while (i < N) {
			if (S[i][j]) j++;
			else if (j > 0 && S[i][j - 1]) j--;
			i++;
		}
		return j;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		S = new boolean[N][M];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			S[a - 1][b - 1] = true;
		}
		btk(0, 0, 0);
		System.out.println(min == 4 ? -1 : min);
	}

}