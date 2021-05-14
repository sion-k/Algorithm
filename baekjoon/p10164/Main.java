package baekjoon.p10164;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N; static int M;
	static int GOAL;
	static int[] cache;

	static int dp(int here) {
		if (here == GOAL) {return 1;}
		if (cache[here] != 0) {return cache[here];}
		if (here % M != 0) {cache[here] += dp(here + 1);}
		if (here + M <= GOAL) {cache[here] += dp(here + M);}
		return cache[here];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		GOAL = N * M;
		cache = new int[GOAL + 1];
		int K = Integer.parseInt(st.nextToken());
		int ans = 0;
		if (K == 0) {ans = dp(1);}
		else {
			ans = dp(K);
			GOAL = K;
			ans *= dp(1);
		}
		System.out.println(ans);
	}

}