package baekjoon.p10251;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N; static int M; static int L;
	static int[][] right; static int[][] down;
	static int[][][][] cache;
	static final int INF = 1000001;

	static boolean inRange(int y, int x) {
		return 0 <= y && y < N &&  0 <= x && x < M;
	}

	// (y, x, d)까지 k번 방향 전환 했을 때, 최소 연료 사용량
	static int dp(int y, int x, int d, int k) {
		if (y == N - 1 && x == M - 1) {return 0;}
		if (cache[y][x][d][k] != 0) {return cache[y][x][d][k];}
		int min = INF;
		int rightCost = d != 0 ? 1 : 0;
		if (inRange(y, x + 1) && k - rightCost >= 0) {
			min = Math.min(min, right[y][x] + dp(y, x + 1, 0, k - rightCost));
		}
		int leftCost = d != 1 ? 1 : 0;
		if (inRange(y + 1, x) && k - leftCost >= 0) {
			min = Math.min(min, down[y][x] + dp(y + 1, x, 1, k - leftCost));
		}
		return cache[y][x][d][k] = min;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			int G = Integer.parseInt(st.nextToken());
			cache = new int[N][M][2][Math.min(N, M) * 2 + 1];
			right = new int[N][M - 1];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < M - 1; j++)
					right[i][j] = Integer.parseInt(st.nextToken());
			}
			down = new int[N - 1][M];
			for (int i = 0; i < N - 1; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < M; j++)
					down[i][j] = Integer.parseInt(st.nextToken());
			}
			boolean reachable = false;
			int maxTurn = Math.min(N, M) * 2;
			for (int k = 1; k <= maxTurn; k++) {
				int cand = Math.min(dp(0, 0, 0, k), dp(0, 0, 1, k));
				if (cand <= G) {
					bw.write(String.valueOf((N - 1 + M - 1) * L + k));
					reachable = true; break;
				}
			}
			if (!reachable) {bw.write("-1");}
			bw.newLine();
		}
		bw.close();
	}

}