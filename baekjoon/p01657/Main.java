package baekjoon.p01657;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static char[][] S;
	static int[][] cache;
	
	static final int[][] price = 
		{{10, 8, 7, 5, 0, 1},
		 { 8, 6, 4, 3, 0, 1},
		 { 7, 4, 3, 2, 0, 1},
		 { 5, 3, 2, 2, 0, 1},
		 { 0, 0, 0, 0, 0, 0},
		 { 1, 1, 1, 1, 0, 0}};
	
	// �� ��ǥ�� �̷���� �κ��� ������ ��ȯ
	static int getPrice(int y1, int x1, int y2, int x2) {
		char p1 = S[y1][x1]; char p2 = S[y2][x2];
		return price[p1 - 'A'][p2 - 'A'];
	}
	
	// i��° ����� �κθ� �߶� �� ���� �� �ִ� �κΰ��� ���� �ִ�
	// �� �̹� ���� ��ġ�� f�� ��Ÿ����
	static int dp(int i, int f) {
		if (i == N) return 0;
		if (cache[i][f] != -1) return cache[i][f];
		return cache[i][f] = btk(i, 0, f, 0);
	}
	
	// i��° �� j��° ������ �κθ� �߶󳪰� �� ���� �� �ִ� �ִ밪
	// ���� �ʵ尡 f�� ���ȣ���Ҷ� �Ѱ��� �ʵ尡 nf
	static int btk(int i, int j, int f, int nf) {
		if (j >= M) return dp(i + 1, nf);
		if ((f & (1 << j)) > 0) return btk(i, j + 1, f, nf); // ������ �� ���� ���
		int max = btk(i, j + 1, f, nf); // �������� �ʴ� ���
		// ���η� �ڸ� �� �ִٸ� �ڸ���
		if (i + 1 < N && getPrice(i, j, i + 1, j) != 0) 
			max = Math.max(max, getPrice(i, j, i + 1, j) + btk(i, j + 1, f, nf | (1 << j)));
		// ���η� �ڸ� �� �ִٸ� �ڸ���
		if (j + 1 < M && (f & (1 << (j + 1))) == 0 && getPrice(i, j, i, j + 1) != 0)
			max = Math.max(max, getPrice(i, j, i, j + 1) + btk(i, j + 2, f, nf));
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N =	Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		S = new char[N][M];
		for (int i = 0; i < N; i++) {
			String row = br.readLine();
			for (int j = 0; j < M; j++) S[i][j] = row.charAt(j);
		}
		cache = new int[N][(int)(Math.pow(2, M))];
		for (int i = 0; i < N; i++) Arrays.fill(cache[i], -1);
		System.out.println(dp(0, 0));
	}

}