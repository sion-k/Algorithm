package baekjoon.p02167;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		// (1, 1) (i, j)로 이루어진 사각형들의 부분 합
		int[][] partSum = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int rowSum = 0;
			for (int j = 1; j <= M; j++) {
				rowSum += Integer.parseInt(st.nextToken());
				partSum[i][j] = partSum[i - 1][j] + rowSum;
			}
		}
		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int y1 = Integer.parseInt(st.nextToken());
			int x1 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int part =
			partSum[y2][x2] - partSum[y1 - 1][x2] - partSum[y2][x1 - 1] + partSum[y1 - 1][x1 - 1];
			bw.write(String.valueOf(part));
			bw.newLine();
		}
		br.close();
		bw.close();
	}

}