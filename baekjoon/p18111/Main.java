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
		// [min, max]의 범위를 모두 시도
		for (int h = minHeight; h <= maxHeight; h++) {
			int cost = 0;
			int block = B;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					int diff = Math.abs(MAP[i][j] - h);
					// 깎아야 하는 경우
					if (MAP[i][j] >= h) {
						cost += (2 * diff);
						block += diff;
					}
					// 채워야 하는 경우
					else {
						cost += diff;
						block -= diff;
					}
				}
			}
			// 캔 블록들과 쌓은 블록들의 합들이 음수면 쌓는게 불가능한 경우임
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