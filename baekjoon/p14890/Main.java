package baekjoon.p14890;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, L;
	static int[][] MAP;
	
	// �־��� ���̵��� ������ ������ �� �ִ��� ��ȯ
	static boolean count(int[] S) {
		boolean[] stair = new boolean[N];
		int here = 0;
		while (here < N - 1) {
			int next = here + 1;
			int diff = S[next] - S[here];
			if (diff == 0) {here++; continue;} // ���̰� ���� ���
			if (Math.abs(diff) >= 2) return false; // 2ĭ �̻� ���̰� ���̳��� ���
			if (diff == 1) { // ������
				if (!inRange(here - L + 1)) return false; // ������ ����� �Ұ�
				// �������� �ʰų� �̹� ���ΰ� ��ġ�Ǿ������� �Ұ�
				for (int d = 0; d < L; d++) if (S[here - d] != S[here] || stair[here - d]) return false;
				for (int d = 0; d < L; d++) stair[here - d] = true;
			} else { // ������
				if (!inRange(here + L)) return false; // ������ ����� �Ұ�
				// �������� ������ �Ұ�
				for (int d = 0; d < L; d++) if (S[here + d + 1] != S[here] - 1) return false;
				for (int d = 0; d < L; d++) stair[here + d + 1] = true;
			}
			here++;
		}
		return true;
	}
	
	static boolean inRange(int i) {return 0 <= i && i < N;}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		MAP = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) MAP[i][j] = Integer.parseInt(st.nextToken());
		}
		int sum = 0;
		for (int i = 0; i < N; i++) if (count(MAP[i])) sum++;
		for (int j = 0; j < N; j++) {
			int[] temp = new int[N];
			for (int t = 0; t < N; t++) temp[t] = MAP[t][j];
			if (count(temp)) sum++;
		}
		System.out.println(sum);
	}
	
}