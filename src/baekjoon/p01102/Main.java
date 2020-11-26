package baekjoon.p01102;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N; static int P;
	static int[][] cost; static int[] cache;
	static final int INF = 801;

	static int dp(int s) {
		if (Integer.bitCount(s) >= P) {return 0;}
		if (cache[s] != -1) {return cache[s];}
		int min = INF;
		for (int i = 0; i < N; i++) {
			if ((s & (1 << i)) > 0) {
				for (int j = 0; j < N; j++) {
					if (i == j) {continue;}
					if ((s & (1 << j)) == 0) {
						int nextState = (s | (1 << j));
						min = Math.min(min, cost[i][j] + dp(nextState));
					}
				}
			}
		}
		return cache[s] = min;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cache = new int[(int)(Math.pow(2, N))];
		Arrays.fill(cache, -1);
		cost = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		String S = br.readLine(); int state = 0;
		for (int i = 0; i < S.length(); i++)
			if (S.charAt(i) == 'Y') state |= (1 << i);
		P = Integer.parseInt(br.readLine());
		int ret = dp(state);
		if (ret == INF) {ret = -1;}
		System.out.println(ret);
	}

}