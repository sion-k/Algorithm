package baekjoon.p11658;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] S = new int[N][N];
		FenwickTree RSQ = new FenwickTree(N);
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				S[i][j] = Integer.parseInt(st.nextToken());
				RSQ.add(i, j, S[i][j]);
			}
		}
		for (int q = 0; q < M; q++) {
			st = new StringTokenizer(br.readLine(), " ");
			int w = Integer.parseInt(st.nextToken());
			if (w == 0) {
				int y = Integer.parseInt(st.nextToken()) - 1;
				int x = Integer.parseInt(st.nextToken()) - 1;
				int c = Integer.parseInt(st.nextToken());
				RSQ.add(y, x, c - S[y][x]);
				S[y][x] = c;
			} else {
				int y1 = Integer.parseInt(st.nextToken()) - 1;
				int x1 = Integer.parseInt(st.nextToken()) - 1;
				int y2 = Integer.parseInt(st.nextToken()) - 1;
				int x2 = Integer.parseInt(st.nextToken()) - 1;
				ans.append(RSQ.sum(y2, x2) - RSQ.sum(y1 - 1, x2) - RSQ.sum(y2, x1 - 1) + RSQ.sum(y1 - 1, x1 - 1)).append("\n");
			}
		}
		System.out.print(ans);
	}
	
}
class FenwickTree {
	long[][] tree;
	
	public FenwickTree(int n) {tree = new long[n + 1][n + 1];}
	
	void add(int y, int x, int val) {
		// 내부적으로는 1-based
		y++; x++;
		while (y < tree.length) {
			int tx = x;
			while (tx < tree.length) {
				tree[y][tx] += val;
				tx += (tx & -tx);
			}
			y += (y & -y);
		}
	}

	long sum(int y, int x) {
		// 내부적으로는 1-based
		y++; x++;
		long ret = 0;
		while (y > 0) {
			int tx = x;
			while (tx > 0) {
				ret += tree[y][tx];
				tx &= (tx - 1);
			}
			y &= (y - 1);
		}
		return ret;
	}
}