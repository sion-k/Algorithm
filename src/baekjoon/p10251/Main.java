package baekjoon.p10251;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
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

	static int dp(int y, int x, int d, int t) {
		if (y == N - 1 && x == M - 1) {return 0;}
		if (cache[y][x][d][t] != 0) {return cache[y][x][d][t];}
		int min = INF;
		int rightCost = ((d != 0) ? 1 : 0) + L;
		if (inRange(y, x + 1) && rightCost <= t) {
			min = Math.min(min, right[y][x] + dp(y, x + 1, 0, t - rightCost));
		}
		int downCost = ((d != 1) ? 1 : 0) + L;
		if (inRange(y + 1, x) && downCost <= t) {
			min = Math.min(min, down[y][x] + dp(y + 1, x, 1, t - downCost));
		}
		return cache[y][x][d][y] = min;
	}

	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("testCase.txt"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			int G = Integer.parseInt(st.nextToken());
			cache = new int[N][M][2][(N + M) * L + (N + M) + 1];
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
			for (int t = 0; t <= (N + M) * L + (N + M); t++) {
				int cand = Math.min(dp(0, 0, 0, t), dp(0, 0, 1, t));
				if (cand <= G) {
					bw.write(String.valueOf(t)); reachable = true; break;
				}
			}
			if (!reachable) {bw.write("-1");}
			bw.newLine();
		}
		bw.close();
	}

}