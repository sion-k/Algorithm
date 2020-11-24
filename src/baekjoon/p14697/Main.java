package baekjoon.p14697;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] R = new int[3];

	static int[] cache;

	static int dp(int N) {
		if (N == 0) {return 1;}
		if (cache[N] != -1) {return cache[N];}
		for (int i = 0; i < 3; i++) {
			int cand = N - R[i];
			if (cand >= 0 && dp(cand) == 1) {
				return cache[N] = 1;
			}
		}
		return cache[N] = 0;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < 3; i++) {R[i] = Integer.parseInt(st.nextToken());}
		int N = Integer.parseInt(st.nextToken());
		cache = new int[N + 1]; Arrays.fill(cache, -1);
		System.out.println(dp(N));
	}

}