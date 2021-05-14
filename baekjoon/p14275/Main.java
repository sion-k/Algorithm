package baekjoon.p14275;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][][] cache;
	
	static final int MOD = 1000000007;
	
	// [i, N)ũ���� ���簢���� ������ �����ϸ鼭 ĥ�ϴ� ����� ��
	// i - 1��° ���� ��ĥ�Ǿ��ִ��� ���� painted
	// i - 1��° ���� ��ĥ�Ǿ��ٸ� ������ ��ĥ�� ĭ�� �� adj
	static int dp(int i, int painted, int adj) {
		if (i == N) {
			for (int j = 0; j < M; j++)
				if ((painted & (1 << j)) > 0)
					if ((adj & (1 << j)) > 0) return 0;
			return 1;
		}
		if (cache[i][painted][adj] != -1) return cache[i][painted][adj];
		return cache[i][painted][adj] = btk(i, 0, painted, adj, 0, adj);
	}
	
	static int btk(int i, int j, int p, int a, int np, int na) {
		if (j == M) return dp(i + 1, np, na);
		int sum = 0;
		// ���� ���� ��ĥ�Ǿ����� �ʴٸ� ��ĥ �ϰų� ���� �� �ִ�
		if ((p & (1 << j)) == 0) {
			sum = (sum + btk(i, j + 1, p, a, np, na)) % MOD;
			// ��ĥ�ϴ� ��� j - 1��°�� ��ĥ�Ǿ����� Ȯ��
			if (j - 1 >= 0 && (np & (1 << (j - 1))) > 0) {
				na ^= (1 << (j - 1));
				na ^= (1 << j);
			}
			sum = (sum + btk(i, j + 1, p, a, np | (1 << j), na)) % MOD;
		// ��ĥ�Ǿ��ְ�, ������ ��ĥ�� ĭ�� ���� ¦����� ĥ�� �� ����
		} else if ((a & (1 << j)) == 0){
			sum = (sum + btk(i, j + 1, p, a, np, na)) % MOD;
		// ��ĥ�Ǿ��ְ�, ������ ��ĥ�� ĭ�� ���� Ȧ����� ĥ�ؾ� �Ѵ�
		} else {
			// ��ĥ�ϴ� ��� j - 1��°�� ��ĥ�Ǿ����� Ȯ��
			if (j - 1 >= 0 && (np & (1 << (j - 1))) > 0) {
				na ^= (1 << (j - 1));
				na ^= (1 << j);
			}
			sum = (sum + btk(i, j + 1, p, a, np | (1 << j), na)) % MOD;
		}
		return sum;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cache = new int[N][1 << M][1 << M];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < (1 << M); j++)
				Arrays.fill(cache[i][j], -1);
		System.out.println(dp(0, 0, 0));
	}
	
}