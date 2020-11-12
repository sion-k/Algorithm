package baekjoon.p02096;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static final int[][] dx = { { 0, 1 }, { -1, 0, 1 }, { -1, 0 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] MAP = new int[N][3];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 3; j++) {
				MAP[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[][] temp = new int[N][3];
		for (int i = 0; i < N; i++) {
			System.arraycopy(MAP[i], 0, temp[i], 0, 3);
		}
		for (int i = N - 2; i >= 0; i--) {
			for (int j = 0; j < 3; j++) {
				int max = 0;
				for (int m = 0; m < dx[j].length; m++) {
					max = Math.max(max, MAP[i + 1][j + dx[j][m]]);
				}
				MAP[i][j] += max;
			}
		}
		int max = 0;
		for (int j = 0; j < 3; j++) {max = Math.max(max, MAP[0][j]);}
		System.out.print(max + " ");
		MAP = temp;
		for (int i = N - 2; i >= 0; i--) {
			for (int j = 0; j < 3; j++) {
				int min = 900000;
				for (int m = 0; m < dx[j].length; m++) {
					min = Math.min(min, MAP[i + 1][j + dx[j][m]]);
				}
				MAP[i][j] += min;
			}
		}
		int min = 900000;
		for (int j = 0; j < 3; j++) {min = Math.min(min, MAP[0][j]);}
		System.out.println(min);
	}

}