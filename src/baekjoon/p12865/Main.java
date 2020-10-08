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
	
	// i��° ���� ���ǵ��� ���ų� ���� �ʾ��� �� ���� �� �ִ� �ִ� ��ġ
	static int dp(int i, int capacity) {
		if (i == N) {return 0;}
		if (cache[i][capacity] != -1) {return cache[i][capacity];}
		// ���� �ʴ� ���
		cache[i][capacity] = dp(i + 1, capacity);
		// ���� ���
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