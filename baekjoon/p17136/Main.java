package baekjoon.p17136;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static boolean[][] MAP;

	static int btk(int[] S) {
		// ���� ���� ���� �� ���ΰ��� ã�´�
		int y = -1; int x = -1;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++)
				if (!MAP[i][j]) {y = i; x = j; break;}
			if (y != -1) break;
		}
		// ��� ������ �ִ� ��� 0
		if (y == -1) return 0;
		int min = 26;
		// ������ ���� ĭ�� n * n ũ���� �����̷� ������ ������ �õ��غ���
		for (int n = 1; n <= 5; n++) {
			if (S[n] > 0 && canCover(y, x, n)) {
				S[n]--;
				cover(y, x, n, true);
				min = Math.min(min, 1 + btk(S));
				S[n]++;
				cover(y, x, n, false);
			}
		}
		return min;
	}

	static boolean inRange(int y, int x) {
		return 0 <= y && y < 10 && 0 <= x && x < 10;
	}

	// (y, x)�� �� ���� ���� �ǵ��� n * n �����̸� ���� �� �ִ��� ����
	static boolean canCover(int y, int x, int n) {
		boolean ret = true;
		for (int dy = 0; dy < n; dy++) {
			for (int dx = 0; dx < n; dx++) {
				int ny = y + dy; int nx = x + dx;
				if (!inRange(ny, nx) || MAP[ny][nx]) {ret = false; break;}
			}
			if (!ret) break;
		}
		return ret;
	}

	// (y, x)�� �� ���� ���� �ǵ��� n * n �����̸� ���̰ų� ����
	static void cover(int y, int x, int n, boolean c) {
		for (int dy = 0; dy < n; dy++)
			for (int dx = 0; dx < n; dx++)
				MAP[y + dy][x + dx] = c;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// ������ �ݴ�� ������ �ִ� ���θ� ����
		MAP = new boolean[10][10];
		for (int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 10; j++)
				MAP[i][j] = st.nextToken().equals("0");
		}
		// 1-based x * x ũ���� �������� ����
		int[] S = new int[6];
		Arrays.fill(S, 5);
		int ret = btk(S);
		System.out.println(ret == 26 ? -1 : ret);
	}

}