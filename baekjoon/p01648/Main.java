package baekjoon.p01648;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] cache;
	
	static final int MOD = 9901;
	
	static int dp(int i, int f) {
		if (i == M) return f == 0 ? 1 : 0;
		if (cache[i][f] != -1) return cache[i][f];
		return cache[i][f] = btk(i, 0, f, 0);
	}
	
	// i��° ���� j��° ����� ������ ä��� ��츦 ��� �õ��Ѵ�
	// �� ���� �̹� ä���� �ִ� ĭ�� p�� ��Ÿ����
	// ��� ä���� dp(i + 1, f)�� ���� ���� ���ؼ� ��ȯ�Ѵ�
	static int btk(int i, int j, int p, int f) {
		if (j == N) return dp(i + 1, f);
		// �̹� ä���� �ִ� ���� ��� �������� �Ѿ��
		if ((p & (1 << j)) > 0) return btk(i, j + 1, p, f);
		// ���η� ��ġ�ϴ� ���
		int sum = btk(i, j + 1, p, f | (1 << j));
		// ���ηε� ��ġ�� �� �ִ� ���
		if (j + 1 < N && (p & (1 << (j + 1))) == 0)
			sum = (sum + btk(i, j + 2, p, f)) % MOD ;
		return sum;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cache = new int[M][(int)(Math.pow(2, N))];
		for (int i = 0; i < M; i++) Arrays.fill(cache[i], -1);
		System.out.println(dp(0, 0));
	}
	
}