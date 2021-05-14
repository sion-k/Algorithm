package baekjoon.p18111;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int[][] MAP = new int[N][M];
		int minHeight = 256; int maxHeight = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				MAP[i][j] = Integer.parseInt(st.nextToken());
				minHeight = Math.min(minHeight, MAP[i][j]);
				maxHeight = Math.max(maxHeight, MAP[i][j]);
			}
		}
		int minCost = 500 * 500 * 256 * 2;
		int height = -1;
		// [min, max]�� ������ ��� �õ�
		for (int h = minHeight; h <= maxHeight; h++) {
			int cost = 0;
			int block = B;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					int diff = Math.abs(MAP[i][j] - h);
					// ��ƾ� �ϴ� ���
					if (MAP[i][j] >= h) {
						cost += (2 * diff);
						block += diff;
					}
					// ä���� �ϴ� ���
					else {
						cost += diff;
						block -= diff;
					}
				}
			}
			// ĵ ��ϵ�� ���� ��ϵ��� �յ��� ������ �״°� �Ұ����� �����
			if (block >= 0) {
				if (minCost >= cost) {
					minCost = cost;
					height = h;
				}
			}
		}
		System.out.println(minCost + " " + height);
	}

}