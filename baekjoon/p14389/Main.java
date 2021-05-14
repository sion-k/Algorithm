package baekjoon.p14389;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] MAP; // i��° ���� �ʱ� ���¸� ����
	static int[][] cache;
	
	// i��° ���� ���°� f�̰�, ������� ���� ���� �� ���� �� �ִ� �ִ� ����
	static int dp(int i, int f) {
		if (i == M) return 0;
		if (cache[i][f] != -1) return cache[i][f];
		return cache[i][f] = btk(i, 0, f, i == M - 1 ? 0 : MAP[i + 1]);
	}
	
	// i��° ���� j��° ����� ������ ä��� ��츦 ��� �õ��Ѵ�
	// �� ���� �̹� ä���� �ִ� ĭ�� p�� ��Ÿ����
	// ��� ä���� dp(i + 1, f)�� ���� ���� ���ؼ� ��ȯ�Ѵ�
	// *4����� ��ġ�� �� ������ ������ ��ġ�ϴ°��� �����ذ� �ƴ�
	// 1..
	// ...
	// ...
	// 1..
	static int btk(int i, int j, int p, int f) {
		if (j == N) return dp(i + 1, f);
		// �̹� ä���� �ִ� ���� ��� �������� �Ѿ��
		if ((p & (1 << j)) > 0) return btk(i, j + 1, p, f);
		// 1����� ��ġ�ϴ� ���
		int max = 1 + btk(i, j + 1, p, f);
		// 4��ϵ� ��ġ �����ϸ� ��ġ�ϴ� ���
		if (i + 1 < M && j + 1 < N && (p & (1 << (j + 1))) == 0 && (f & (1 << j)) == 0 && (f & (1 << (j + 1))) == 0) {
			f |= (1 << j); f |= (1 << (j + 1));
			max = Math.max(max, 16 + btk(i, j + 2, p, f));			
		}
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		boolean[][] temp = new boolean[N][M];
		int sum = 0;
		for (int i = 0; i < N; i++) {
			String row = br.readLine();
			for (int j = 0; j < M; j++)
				if (row.charAt(j) == '1') {sum++; temp[i][j] = true;}
		}
		MAP = new int[M];
		for (int j = 0; j < M; j++) {
			int f = 0;
			for (int i = 0; i < N; i++) if (temp[i][j]) f |= (1 << i);
			MAP[j] = f;
		}
		cache = new int[M][(int)(Math.pow(2, N))];
		for (int i = 0; i < M; i++) Arrays.fill(cache[i], -1);
		System.out.println(sum + dp(0, MAP[0]));
	}
	
}