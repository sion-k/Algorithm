package baekjoon.p11660;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int[][] pSum;

	static int query(int y1, int x1, int y2, int x2) {
		return pSum[y2][x2] - pSum[y1 -1][x2] - pSum[y2][x1 - 1] + pSum[y1 - 1][x1 - 1];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		pSum = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int rowSum = 0;
			for (int j = 1; j <= N; j++) {
				rowSum += Integer.parseInt(st.nextToken());
				pSum[i][j] = rowSum + pSum[i - 1][j];
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int y1 = Integer.parseInt(st.nextToken());
			int x1 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			bw.write(String.valueOf(query(y1, x1, y2, x2)));
			bw.newLine();
		}
		bw.close();

	}
}