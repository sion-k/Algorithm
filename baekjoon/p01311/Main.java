package baekjoon.p01311;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N; static int[][] D;
	static int[][] cache;
	static final int INF = 200000;

	// ���� ���� �ð� �ִ� ����� ��� ��Ʈ�ʵ尡 f�� ��
	// [j, )�� ���� ��� ó���ϴµ� �ʿ��� �ּ� ���
	static int dp(int f, int j) {
		if (j >= N) return 0;
		if (cache[f][j] != 0) return cache[f][j];
		int min = INF;
		for (int i = 0; i < N; i++)
			// i��° ����� ���� ����ϰ� ���� ���� ���
			if ((f & (1 << i)) == 0)
				min = Math.min(min, D[i][j] + dp(f | (1 << i), j + 1));
		return cache[f][j] = min;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		D = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++)
				D[i][j] = Integer.parseInt(st.nextToken());
		}
		cache = new int[(int)Math.pow(2, N)][N];
		bw.write(String.valueOf(dp(0, 0)));
		bw.newLine();
		bw.close();
	}

}