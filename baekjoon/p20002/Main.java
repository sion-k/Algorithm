package baekjoon.p20002;

import java.io.*;
import java.util.*;

public class Main {
	static int[][] forest;
	static int[][] partSum;

	static int getSum(int r1, int c1, int r2, int c2) {
		if (r1 == r2 && c1 == c2) {
			return forest[r2][c2];
		}
		if (r1 == 0 && c1 == 0) {
			return partSum[r2][c2];
		}

		return partSum[r2][c2] + partSum[r1 - 1][c1 - 1] - 
			   partSum[r1 - 1][c2] - partSum[r2][c1 - 1] ;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());

		// forest의 0행은 0, 0열은 0으로 정의한다
		forest = new int[N + 1][N + 1];
		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j < N + 1; j++) {
				forest[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		partSum = new int[N + 1][N + 1];
		
		int rowSum = 0;
		for (int i = 1; i < N + 1; i++) {
			rowSum = 0;
			for (int j = 1; j < N + 1; j++) {
				rowSum += forest[i][j];
				partSum[i][j] = partSum[i - 1][j] + rowSum;
			}
		}
		
		int max = -1000 * 300 * 300;
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				int ti = i;
				int tj = j;
				while (1 <= ti && ti <= N && 1 <= tj && tj <= N) {
					max = Math.max(max, getSum(i, j, ti, tj));
					ti++; tj++;
				}
			}
		}
		br.close();
		System.out.println(max);
	}

}

