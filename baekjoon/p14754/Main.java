package baekjoon.p14754;

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
		int[][] MAP = new int[N][M];
		int[] rowMax = new int[N];
		int[] colMax = new int[M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				MAP[i][j] = Integer.parseInt(st.nextToken());
				rowMax[i] = Math.max(rowMax[i], MAP[i][j]);
				colMax[j] = Math.max(colMax[j], MAP[i][j]);
			}
		}

		long sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (MAP[i][j] != rowMax[i] && MAP[i][j] != colMax[j]) {
					sum += MAP[i][j];
				}
			}
		}
		System.out.println(sum);
	}

}