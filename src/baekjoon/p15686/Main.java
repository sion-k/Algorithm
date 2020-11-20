package baekjoon.p15686;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] MAP;
	static List<int[]> chicken;

	static int dist(int y1, int x1, int y2, int x2) {
		return Math.abs(y1 - y2) + Math.abs(x1 - x2);
	}

	static int minDist(int y, int x) {
		int min = 100;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (MAP[i][j] == 2) {
					min = Math.min(min, dist(y, x, i, j));
				}
			}
		}
		return min;
	}

	static int cityDist(List<int[]> picked) {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (MAP[i][j] == 1) {
					int min = 100;
					for (int[] chicken : picked) {
						int y = chicken[0]; int x = chicken[1];
						min = Math.min(min, dist(i, j, y, x));
					}
					sum += min;
				}
			}
		}
		return sum;
	}

	static int min = 250000;

	static void BFC(int i, int toPick, List<int[]> picked) {
		if (i == chicken.size()) {
			if (toPick == 0) {min = Math.min(min, cityDist(picked));}
			return;
		}
		BFC(i + 1, toPick, picked);
		picked.add(chicken.get(i));
		BFC(i + 1, toPick - 1, picked);
		picked.remove(picked.size() - 1);
 	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		MAP = new int[N][N];
		chicken = new ArrayList<>();
		int M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				MAP[i][j] = Integer.parseInt(st.nextToken());
				if (MAP[i][j] == 2) {chicken.add(new int[] {i, j});}
			}
		}
		List<int[]> picked = new ArrayList<>();
		BFC(0, M, picked);
		System.out.println(min);
	}

}