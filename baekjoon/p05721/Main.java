package baekjoon.p05721;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N; static int M;
	static int[][] MAP;
	static int[] cache;
	static int[][] colMax;

	// i번째 행에서 시작해서 얻을 수 있는 최대 사탕 수
	static int dp(int i) {
		if (i >= N) return 0;
		if (cache[i] != 0) return cache[i];
		return cache[i] =
		Math.max(dp(i + 1), colMax[i][0] + dp(i + 2));
	}

	// (i, j)에서 시작해서 i번째 행에서 얻을 수 있는 최대 사탕 수
	static int dp2(int i, int j) {
		if (j >= M) return 0;
		if (colMax[i][j] != 0) return colMax[i][j];
		return colMax[i][j] =
		Math.max(dp2(i, j + 1), MAP[i][j] + dp2(i, j + 2));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String line = "";
		while (!(line = br.readLine()).equals("0 0")) {
			StringTokenizer st = new StringTokenizer(line, " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			MAP = new int[N][M];
			cache = new int[N]; colMax = new int[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < M; j++)
					MAP[i][j] = Integer.parseInt(st.nextToken());
			}
			for (int i = 0; i < N; i++) dp2(i, 0);
			bw.write(String.valueOf(dp(0)));
			bw.newLine();
		}
		bw.close();
	}

}