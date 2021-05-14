package baekjoon.p02494;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N;
	static int[] S; static int[] G;
	static int[][] cache;

	static final int INF = 987654321;

	// [i, ) ������ ���� ���縦 ���ϴ� ���±��� ����µ� �ּ� ȸ�� ĭ ��
	// [i, ) ������ ���� ������� k[0, 9]�� �߰��� ������ �ִ� �����̴�
	static int dp(int i, int k) {
		if (i == N) return 0;
		if (cache[i][k] != -1) return cache[i][k];
		int here = (S[i] + k) % 10;
		// �̹� ������ �ִ� ���
		if (here == G[i]) return cache[i][k] = dp(i + 1, k);
		int min = INF;
		// �������� ȸ�����Ѽ� ���ߴ� ���
		int leftCost = (G[i] - here + 10) % 10;
		min = Math.min(min, leftCost + dp(i + 1, (k + leftCost) % 10));
		// ���������� ȸ�����Ѽ� ���ߴ� ���
		int rightCost = (here - G[i] + 10) % 10;
		min = Math.min(min, rightCost + dp(i + 1, k));
		return cache[i][k] = min;
	}

	static StringBuilder ans = new StringBuilder();

	static void reconstruct(int i, int k) {
		int here = (S[i] + k) % 10;
		// �������� ȸ�����Ѽ� ���ߴ� ���
		int leftCost = (G[i] - here + 10) % 10;
		// ���������� ȸ�����Ѽ� ���ߴ� ���
		int rightCost = (here - G[i] + 10) % 10;
		// �� �������� ���
		if (i == N - 1) {
			// �������� �����°� �������� ��� (���� ��쵵 ����)
			if (leftCost <= rightCost) {
				ans.append(i + 1).append(" ").append(leftCost).append("\n");
			} else {
				ans.append(i + 1).append(" ").append(-rightCost).append("\n");
			}
			return;
		}
		// �������� �����°� �������� ��� (���� ��쵵 ����)
		if(leftCost + cache[i + 1][(k + leftCost) % 10] <= rightCost + cache[i + 1][k]) {
			ans.append(i + 1).append(" ").append(leftCost).append("\n");
			reconstruct(i + 1, (k + leftCost) % 10);
		} else {
			ans.append(i + 1).append(" ").append(-rightCost).append("\n");
			reconstruct(i + 1, k);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		S = new int[N]; G = new int[N];
		String L = br.readLine();
		for (int i = 0; i < L.length(); i++)
			S[i] = L.charAt(i) - '0';
		L = br.readLine();
		for (int i = 0; i < L.length(); i++)
			G[i] = L.charAt(i) - '0';
		cache = new int[N][10];
		for (int i = 0; i < N; i++)
			Arrays.fill(cache[i], -1);
		ans.append(dp(0, 0)).append("\n");
		reconstruct(0, 0);
		System.out.print(ans);
	}

}