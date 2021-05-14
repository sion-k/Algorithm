package baekjoon.p02616;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] S; static int N;
	static int[] pSum;
	static int C;
	static int[][] cache;

	// [i, ...] �������� k���� ���� ������� �̿��� ����� �� �ִ� �ִ� �մ� ��
	static int dp(int i, int k) {
		if (i == N + 1 || k == 0) {return 0;}
		if (cache[i][k] != -1) {return cache[i][k];}
		int max = 0;
		// i���� ���� ������� ����ϴ� ���
		int tail = Math.min(N, i + C - 1);
		max = Math.max(max, pSum[tail] - pSum[i - 1] + dp(tail + 1, k - 1));
		// i + 1���� ��� ȣ��
		max = Math.max(max, dp(i + 1, k));
		return cache[i][k] = max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		S = new int[N + 1]; pSum = new int[N + 1];
		cache = new int[N + 1][4];
		for (int i = 0; i < N + 1; i++)
			Arrays.fill(cache[i], -1);
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			S[i] = Integer.parseInt(st.nextToken());
			pSum[i] = pSum[i - 1] + S[i];
		}
		C = Integer.parseInt(br.readLine());
		System.out.println(dp(1, 3));
	}

}