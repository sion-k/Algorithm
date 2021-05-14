package codeforce.r697;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class E {
	static int[][] cache;

	static final int MOD = 1000000007;

	static int dp(int n, int r) {
		if (n == r || r == 0) return 1;
		if (cache[n][r] != 0) return cache[n][r];
		return cache[n][r] = (dp(n - 1, r - 1) + dp(n - 1 , r)) % MOD;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		cache = new int[1001][1001];
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine(), " ");
			int[] S = new int[N];
			for (int i = 0; i < N; i++)
				S[i] = Integer.parseInt(st.nextToken());
			Arrays.sort(S);
			// ��� ���Ұ� ���� �ٸ��ٸ� �ִ밪�� �����ϴ� ����� ���� 1��������
			// ���ҿ� �ߺ��� �����Ѵٸ� �ڿ��� K��° ���� ���� ����� ���� ����ؾ���
			// �ڿ��� K��° ���� ������ ����
			int cnt = 0;
			for (int i = 0; i < N; i++)
				if (S[i] == S[N - K])
					cnt++;
			// K��° ������ ��� ���� �ϴ��� ���
			int r = K;
			for (int i = 0; i < N; i++)
				if (S[i] > S[N - K])
					r--;
			bw.write(String.valueOf(dp(cnt, r)));
			bw.newLine();
		}
		bw.close();
	}

}