package baekjoon.p13398;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N; static int[] S;
	static int[][][] cache;
	static final int NINF = -1000000001;

	static int dp(int r, int p, int i) {
		if (i >= N) {return 0;}
		if (cache[r][p][i] != NINF) {return cache[r][p][i];}
		int max = S[i] + dp(r, 1, i + 1);
		if (p == 0) {max = Math.max(max, dp(r, p, i + 1));}
		else {
			max = Math.max(max, 0);
			if (r == 1) {max = Math.max(max, dp(0, p, i + 1));}
		}
		return cache[r][p][i] = max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); S = new int[N];
		cache = new int[2][2][N];
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++)
				Arrays.fill(cache[i][j], NINF);
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {S[i] = Integer.parseInt(st.nextToken());}
		int max = NINF;
		for (int i = 0; i < N; i++) {max = Math.max(max, S[i] + dp(1, 1, i + 1));}
		System.out.println(max);
	}

}