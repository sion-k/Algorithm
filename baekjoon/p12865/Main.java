package baekjoon.p12865;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] W;
	static int[] V;
	static int[][] cache;
	
	// i번째 이후 물건들을 고르거나 고르지 않았을 때 얻을 수 있는 최대 가치
	static int dp(int i, int capacity) {
		if (i == N) {return 0;}
		if (cache[i][capacity] != -1) {return cache[i][capacity];}
		// 고르지 않는 경우
		cache[i][capacity] = dp(i + 1, capacity);
		// 고르는 경우
		if (W[i] <= capacity) {
			cache[i][capacity] = Math.max(cache[i][capacity],
			V[i] + dp(i + 1, capacity - W[i]));
		}
		return cache[i][capacity];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		W = new int[N]; V = new int[N];
		int K = Integer.parseInt(st.nextToken());
		cache = new int[N][100001];
		for (int i = 0; i < N; i++) {Arrays.fill(cache[i], -1);}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}
		br.close();
		System.out.println(dp(0, K));
	}

}