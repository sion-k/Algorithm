package baekjoon.p01912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] S;
	static int[] cache;

	// i부터 선택할 수 있는 연속합중 최대
	static int dp(int i) {
		if (i >= N) {return 0;}
		if (cache[i] != -10000001) {return cache[i];}
		return cache[i] = Math.max(dp(i + 1), S[i] + dp(i + 1));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		S = new int[N]; cache = new int[N]; Arrays.fill(cache, -10000001);
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0 ; i < N; i++) {S[i] = Integer.parseInt(st.nextToken());}
		System.out.println(dp(0));
	}

}