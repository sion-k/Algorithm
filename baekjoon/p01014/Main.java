package baekjoon.p01014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static boolean[][] MAP; // ���� �� �ִ��� ����
	static int[][] cache;
	
	// i - 1��° �࿡ ���� �л����� ������ f�϶�, i��° ����� ���� �� �ִ� �л��� �ִ� ��
	static int dp(int i, int f) {
		if (i == N) return 0;
		if (cache[i][f] != -1) return cache[i][f];
		return cache[i][f] = btk(i, 0, f, 0);
	}
	
	// i��° ��, j��° ������ �л��� ���� �� ���� �� �ִ� �ִ� ��
	// i - 1��° ���� ���� f, �׸��� ��� ȣ��ÿ� �Ѱ��� ���� ���� ���� nf
	static int btk(int i, int j, int f, int nf) {
		if (j >= M) return dp(i + 1, nf);
		int max = btk(i, j + 1, f, nf); // ���� �ʴ� ���
		if (MAP[i][j]) {
			// �� �� ���� ó��
			if (j == 0) {if ((f & (1 << (j + 1))) == 0) max = Math.max(max, 1 + btk(i, j + 2, f, nf | (1 << j)));}
			else if (j == M - 1) {if ((f & (1 << (j - 1))) == 0) max = Math.max(max, 1 + btk(i, j + 2, f, nf | (1 << j)));}
			// �Ϲ����� ���
			else if ((f & (1 << (j - 1))) == 0 && (f & (1 << (j + 1))) == 0) {max = Math.max(max, 1 + btk(i, j + 2, f, nf | (1 << j)));}
		}
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			MAP = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				String row = br.readLine();
				for (int j = 0; j < M; j++) MAP[i][j] = (row.charAt(j) == '.'); 
			}
			cache = new int[N][(int)(Math.pow(2, M))];
			for (int i = 0; i < N; i++) Arrays.fill(cache[i], -1);
			sb.append(dp(0, 0)).append("\n");
		}
		System.out.print(sb);
	}
	
}