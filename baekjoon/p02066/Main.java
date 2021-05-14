package baekjoon.p02066;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// i��° �׷��� j��° ī���� ���ڸ� ����
	static int[][] S = new int[9][4];
	static double[] cache;
	static final int FULL = (int)(Math.pow(2, 27)) - 1;

	// ��Ʈ����ŷ�� ���� 9���� �׷��� 3��Ʈ�� �������� 27��Ʈ �ʵ带 �����Ѵ�
	// 000000000/00/00/00/00/00/00/00/00/00
	// 876543210 8  7  6  5  4  3  2  1  0 ��°�� �׷��� ���� ���� �ִ� ī�尡 ���° ī������ ����
	// �� i��° �׷쿡 ���� ī�尡 ������� Ȱ��ȭ
	// �ʵ��� ���°� f�� ���� ��, ���̿� ������ Ȯ�� ��ȯ [0 ~ 1.0]
	static double dp(int f) {
		// ��� ī�带 �� ���
		if (f == FULL) return 1.0;
		if (Double.compare(cache[f], -1) != 0) return cache[f];
		return 0;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		cache = new double[(int)(Math.pow(2, 27))];
		for (int i = 0; i < 9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 4; j++) {

			}
		}
	}

}
